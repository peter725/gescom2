package es.dgc.gesco.model.modules.user.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;
import es.dgc.gesco.model.commom.validation.constraints.NIF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements LongIdModel {

    private Long id;

    private Integer state = EntityState.ON.getValue();

    private String name;

    private String nif;

    private String password;

    private String email;

    private AutonomousCommunity autonomousCommunity;

    private Profile profile;

    private UserType userType;

    private String phone;

    private String firstSurname;

    private String secondSurname;

    public String getFullName() {
        String fullName = getName() + " " + getFirstSurname() + " " + getSecondSurname();
        return fullName;
    }
}

