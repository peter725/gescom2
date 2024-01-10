package es.consumo.junta_arbitral.modules.role.model.dto;

import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;
    private List<ModuleEntity> module;
    private List<PermissionModuleDTO> modules;
}
