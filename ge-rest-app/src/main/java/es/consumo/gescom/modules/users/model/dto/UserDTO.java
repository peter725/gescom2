package es.consumo.gescom.modules.users.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.constants.EntityState;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.profile.model.dto.ProfileDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.userType.model.dto.UserTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String lastSurname;
    private String email;
    private String phone;
    private ProfileDTO profile;
    private AutonomousCommunityDTO autonomousCommunity;
    private UserTypeDTO userType;
    private RoleEntity role;
    private Integer state = EntityState.ON.getValue();
}
