package es.consumo.gescom.modules.role.model.dto;

import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
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
