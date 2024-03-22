package es.consumo.gescom.modules.role.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.module.repository.ModuleRepository;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import es.consumo.gescom.modules.permission.repository.PermissionRepository;
import es.consumo.gescom.modules.role.model.constants.PermissionScope;
import es.consumo.gescom.modules.role.model.dto.PermissionModuleNewDTO;
import es.consumo.gescom.modules.role.model.dto.RoleNewDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import es.consumo.gescom.modules.role.repository.RoleHasModuleRepository;
import es.consumo.gescom.modules.role.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends EntityCrudService<RoleEntity, Long> implements RoleService {


    private final RoleHasModuleRepository roleHasModuleRepository;
    private final PermissionRepository permissionRepository;
    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(GESCOMRepository<RoleEntity, Long> repository, RoleHasModuleRepository roleHasModuleRepository,
                           PermissionRepository permissionRepository, ModuleRepository moduleRepository,
                           ModelMapper modelMapper) {
        super(repository);
        this.roleHasModuleRepository = roleHasModuleRepository;
        this.permissionRepository = permissionRepository;
        this.moduleRepository = moduleRepository;
        this.modelMapper = modelMapper;
    }
    
    public RoleNewDTO findRoleById(Long idRole) {
    	RoleEntity roleEntity = findById(idRole).orElseThrow();
    	List<RoleHasModuleEntity> roleHasModules = roleHasModuleRepository.findByRoleId(idRole);
    	
    	RoleNewDTO response = new RoleNewDTO();
    	response.setId(idRole);
    	response.setName(roleEntity.getName());
    	
    	List<PermissionModuleNewDTO> modules = new ArrayList<>();
    	Map<ModuleEntity, List<RoleHasModuleEntity>> itemsByTeam = 
    			roleHasModules.stream().collect(Collectors.groupingBy(item -> item.getModule()));
    	
    	for (Map.Entry<ModuleEntity, List<RoleHasModuleEntity>> entry : itemsByTeam.entrySet()) {
    		PermissionModuleNewDTO permission = new PermissionModuleNewDTO();
    		permission.setModule(entry.getKey());
    		List<PermissionEntity> permissions = new ArrayList<>();
    		for (RoleHasModuleEntity roleHasModule : entry.getValue()) {
    			permissions.add(roleHasModule.getPermission());
    		}
    		permission.setPermissions(permissions);
    		modules.add(permission);
    	}
    	
    	response.setModules(modules);
    	return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleEntity create(RoleNewDTO roleDTO) {
        RoleEntity roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
        roleEntity.setEnable(true);
        roleEntity.setVisible(true);
        roleEntity.setUpdateAt(null);
        roleEntity = repository.save(roleEntity);
        final List<RoleHasModuleEntity> roleHasModules = new ArrayList<>();
        final Map<Long, List<PermissionEntity>> map = permissionRepository.findAll().stream()
                .collect(Collectors.groupingBy(PermissionEntity::getId));

        for (PermissionModuleNewDTO permissionModuleDTO : roleDTO.getModules()) {
            final ModuleEntity module = moduleRepository.findById(permissionModuleDTO.getModule().getId()).orElseThrow();
            iterateAndSaveRoleHasModuleEntityCreate(permissionModuleDTO.getPermissions(), roleHasModules, map, roleEntity, module);
        }
        if (!roleHasModules.isEmpty())
            roleHasModuleRepository.saveAll(roleHasModules);
        return roleEntity;
    }
    
    private void iterateAndSaveRoleHasModuleEntityCreate(List<PermissionEntity> permissionDTOS, List<RoleHasModuleEntity> toSave,
            Map<Long, List<PermissionEntity>> map, RoleEntity entity, ModuleEntity module) {
    	for (PermissionEntity permissionDTO : permissionDTOS) {
    		final PermissionEntity permission = map.get(permissionDTO.getId()).get(0);
    		toSave.add(createRoleHasModuleEntity(entity.getId(), module, permission, PermissionScope.ALL));
    	}
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleEntity update(RoleNewDTO roleDTO) {
        final RoleEntity entity = findById(roleDTO.getId()).orElseThrow();
        modelMapper.map(roleDTO, entity);
        final Map<Long, List<PermissionEntity>> map = permissionRepository.findAll().stream()
                .collect(Collectors.groupingBy(PermissionEntity::getId));
        final List<RoleHasModuleEntity> toSave = new ArrayList<>();
        final List<Long> toDelete = new ArrayList<>();
        List<Long> moduleIDs = new ArrayList<Long>();
        for (PermissionModuleNewDTO permissionModuleDTO : roleDTO.getModules()) {
            final ModuleEntity module = moduleRepository.findById(permissionModuleDTO.getModule().getId()).orElseThrow();
            moduleIDs.add(module.getId());
            iterateAndSaveRoleHasModuleEntityUpdate(permissionModuleDTO.getPermissions(), toDelete, toSave, map, entity, module);
        }
        toDelete.addAll(roleHasModuleRepository.findIdByRoleIdAndModuleIdNotIn(entity.getId(), moduleIDs));
        if (!toDelete.isEmpty())
            roleHasModuleRepository.deleteAllById(toDelete);
        if (!toSave.isEmpty())
            roleHasModuleRepository.saveAll(toSave);
        return repository.save(entity);
    }
    
    private void iterateAndSaveRoleHasModuleEntityUpdate(List<PermissionEntity> permissionDTOS, List<Long> toDelete, List<RoleHasModuleEntity> toSave,
            Map<Long, List<PermissionEntity>> map, RoleEntity entity, ModuleEntity module) {
    	
    	List<Long> permissionIDs = new ArrayList<Long>();
    	for (PermissionEntity permissionDTO : permissionDTOS) {
    		permissionIDs.add(permissionDTO.getId());
    		Optional<RoleHasModuleEntity> roleHasModules = roleHasModuleRepository.findByRoleIdAndPermissionIdAndModuleId(entity.getId(), permissionDTO.getId(), module.getId());
    		if (roleHasModules.isPresent()) {
    			roleHasModules.get().setPermission(permissionDTO);
    			toSave.add(roleHasModules.get());
    		} else {
    			final PermissionEntity permission = map.get(permissionDTO.getId()).get(0);
    			toSave.add(createRoleHasModuleEntity(entity.getId(), module, permission, PermissionScope.ALL));
    		}
    	}
    	
    	toDelete.addAll(roleHasModuleRepository.findIdByRoleIdAndModuleIdAndPermissionIdNotIn(entity.getId(), permissionIDs, module.getId()));
    }

//    private void iterateAndSaveRoleHasModuleEntity(List<PermissionEntity> permissionDTOS, List<Long> toDelete, List<RoleHasModuleEntity> toSave,
//                                                   Map<Long, List<PermissionEntity>> map, RoleEntity entity, ModuleEntity module) {
//        for (PermissionEntity permissionDTO : permissionDTOS) {
//            if (permissionDTO.getId() != 0 && !permissionDTO.isCheck()) {
//                if (Objects.nonNull(toDelete))
//                    toDelete.add(permissionDTO.getId());
//            } else {
//                if (permissionDTO.getId() != 0 && permissionDTO.isCheck()) {
//                    RoleHasModuleEntity roleHasModule = roleHasModuleRepository.findById(permissionDTO.getId()).orElseThrow();
//                    roleHasModule.setScope(permissionDTO.getScope());
//                    toSave.add(roleHasModule);
//                } else if (permissionDTO.getId() == 0 && permissionDTO.isCheck()) {
//                    final PermissionEntity permission = map.get(permissionDTO.getPermission().getId()).get(0);
//                    toSave.add(createRoleHasModuleEntity(entity.getId(), module, permission, permissionDTO.getScope()));
//                } else {
//                    throw new RuntimeException("asd");
//                }
//            }
//        }
//    }

    private RoleHasModuleEntity createRoleHasModuleEntity(Long rolId, ModuleEntity module,
                                                          PermissionEntity permission, PermissionScope scope) {
        RoleHasModuleEntity roleHasModuleEntity = new RoleHasModuleEntity();
        roleHasModuleEntity.setRoleId(rolId);
        roleHasModuleEntity.setModule(module);
        roleHasModuleEntity.setPermission(permission);
        roleHasModuleEntity.setScope(scope);
        return roleHasModuleEntity;
    }
}
