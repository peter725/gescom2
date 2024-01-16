package es.consumo.gescom.modules.role.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
import es.consumo.gescom.modules.role.model.dto.PermissionModuleDTO;
import es.consumo.gescom.modules.role.model.dto.RoleDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import es.consumo.gescom.modules.role.repository.RoleHasModuleRepository;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleConverter extends SimpleDataConverter<RoleEntity, RoleDTO> {
    private final RoleHasModuleRepository roleHasModuleRepository;

    public RoleConverter(ModelMapper modelMapper, RoleHasModuleRepository roleHasModuleRepository) {
        super(modelMapper);
        this.roleHasModuleRepository = roleHasModuleRepository;
    }

    @Override
    public RoleDTO convertToModel(RoleEntity entity) {

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(entity.getId());
        roleDTO.setName(entity.getName());
        final Map<Long, ModuleEntity> modules = new HashMap<>();
        final List<RoleHasModuleEntity> roleHasModuleEntities = roleHasModuleRepository.findByRoleId(entity.getId());

        Map<Long, PermissionModuleDTO> modulePermissionMap = new HashMap<>();
        for (RoleHasModuleEntity roleHasModuleEntity : roleHasModuleEntities) {
            Long moduleId = roleHasModuleEntity.getModule().getId();
            PermissionModuleDTO permissionModuleDTO = modulePermissionMap.computeIfAbsent(moduleId, k -> {
                PermissionModuleDTO newPermissionModuleDTO = new PermissionModuleDTO();
                newPermissionModuleDTO.setId(roleHasModuleEntity.getModule().getId());
                newPermissionModuleDTO.setName(roleHasModuleEntity.getModule().getName());
                newPermissionModuleDTO.setPermissions(new ArrayList<>());
                return newPermissionModuleDTO;
            });

            modules.computeIfAbsent(moduleId, k -> roleHasModuleEntity.getModule());
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setId(roleHasModuleEntity.getId());
            permissionDTO.setScope(roleHasModuleEntity.getScope());
            Hibernate.initialize(roleHasModuleEntity.getPermission());
            permissionDTO.setPermission(roleHasModuleEntity.getPermission());
            permissionModuleDTO.getPermissions().add(permissionDTO);
        }

        roleDTO.setModule(new ArrayList<>(modules.values()));
        roleDTO.setModules(new ArrayList<>(modulePermissionMap.values()));

        return roleDTO;
    }
}
