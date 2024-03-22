package es.consumo.gescom.modules.role.model.dto;

import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionModuleNewDTO {
    private ModuleEntity module;
    private List<PermissionEntity> permissions;
}
