package es.consumo.gescom.modules.role.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.module.repository.ModuleRepository;
import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import es.consumo.gescom.modules.permission.repository.PermissionRepository;
import es.consumo.gescom.modules.role.model.constants.PermissionScope;
import es.consumo.gescom.modules.role.model.dto.PermissionModuleDTO;
import es.consumo.gescom.modules.role.model.dto.RoleDTO;
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
import java.util.Objects;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleEntity create(RoleDTO roleDTO) {
        RoleEntity roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
        roleEntity.setEnable(true);
        roleEntity.setVisible(true);
        roleEntity.setUpdateAt(null);
        roleEntity = repository.save(roleEntity);
        final List<RoleHasModuleEntity> roleHasModules = new ArrayList<>();
        final Map<Long, List<PermissionEntity>> map = permissionRepository.findAll().stream()
                .collect(Collectors.groupingBy(PermissionEntity::getId));

        for (PermissionModuleDTO permissionModuleDTO : roleDTO.getModules()) {
            final ModuleEntity module = moduleRepository.findById(permissionModuleDTO.getId()).orElseThrow();
            iterateAndSaveRoleHasModuleEntity(permissionModuleDTO.getPermissions(), null, roleHasModules, map, roleEntity, module);
        }
        if (!roleHasModules.isEmpty())
            roleHasModuleRepository.saveAll(roleHasModules);
        return roleEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleEntity update(RoleDTO roleDTO) {
        final RoleEntity entity = findById(roleDTO.getId()).orElseThrow();
        modelMapper.map(roleDTO, entity);
        final Map<Long, List<PermissionEntity>> map = permissionRepository.findAll().stream()
                .collect(Collectors.groupingBy(PermissionEntity::getId));
        final List<RoleHasModuleEntity> toSave = new ArrayList<>();
        final List<Long> toDelete = new ArrayList<>();
        for (PermissionModuleDTO permissionModuleDTO : roleDTO.getModules()) {
            final ModuleEntity module = moduleRepository.findById(permissionModuleDTO.getId()).orElseThrow();
            iterateAndSaveRoleHasModuleEntity(permissionModuleDTO.getPermissions(), toDelete, toSave, map, entity, module);
        }
        if (!toDelete.isEmpty())
            roleHasModuleRepository.deleteAllById(toDelete);
        if (!toSave.isEmpty())
            roleHasModuleRepository.saveAll(toSave);
        return repository.save(entity);
    }

    private void iterateAndSaveRoleHasModuleEntity(List<PermissionDTO> permissionDTOS, List<Long> toDelete, List<RoleHasModuleEntity> toSave,
                                                   Map<Long, List<PermissionEntity>> map, RoleEntity entity, ModuleEntity module) {
        for (PermissionDTO permissionDTO : permissionDTOS) {
            if (permissionDTO.getId() != 0 && !permissionDTO.isCheck()) {
                if (Objects.nonNull(toDelete))
                    toDelete.add(permissionDTO.getId());
            } else {
                if (permissionDTO.getId() != 0 && permissionDTO.isCheck()) {
                    RoleHasModuleEntity roleHasModule = roleHasModuleRepository.findById(permissionDTO.getId()).orElseThrow();
                    roleHasModule.setScope(permissionDTO.getScope());
                    toSave.add(roleHasModule);
                } else if (permissionDTO.getId() == 0 && permissionDTO.isCheck()) {
                    final PermissionEntity permission = map.get(permissionDTO.getPermission().getId()).get(0);
                    toSave.add(createRoleHasModuleEntity(entity.getId(), module, permission, permissionDTO.getScope()));
                } else {
                    throw new RuntimeException("asd");
                }
            }
        }
    }

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
