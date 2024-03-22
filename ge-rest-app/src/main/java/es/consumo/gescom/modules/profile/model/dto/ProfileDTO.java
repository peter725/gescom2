package es.consumo.gescom.modules.profile.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import es.consumo.gescom.modules.role.model.constants.PermissionScope;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO implements Serializable, LongIdModel {

    private Long id;
    private String name;
    private List<PermissionDTO> permissions;

}
