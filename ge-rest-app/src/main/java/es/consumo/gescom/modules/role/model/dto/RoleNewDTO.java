package es.consumo.gescom.modules.role.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleNewDTO {

    private Long id;
    private String name;
    private List<PermissionModuleNewDTO> modules;
}
