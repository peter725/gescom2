package es.consumo.junta_arbitral.modules.role.model.dto;

import es.consumo.junta_arbitral.modules.module.model.dto.ModuleDTO;
import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;
import es.consumo.junta_arbitral.modules.permission.model.dto.PermissionDTO;
import es.consumo.junta_arbitral.modules.permission.model.entity.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionModuleDTO {

    private long id;
    private String name;
    private List<PermissionDTO> permissions;
}
