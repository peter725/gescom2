package es.consumo.gescom.modules.users.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.modules.role.model.entity.RoleEntity;
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
    private List<RoleEntity> roles;
}
