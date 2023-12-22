package es.dgc.gesco.model.modules.user.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements LongIdModel {

    Long id;

    Integer state = EntityState.ON.getValue();

    String name;

    String nif;

    String password;

    String email;

    AutonomousCommunity autonomousCommunity;

    Profile profile;

    UserType userType;

    String phone;

    String firstSurname;

    String secondSurname;

    public String getFullName() {
        String fullName = getName() + " " + getFirstSurname() + " " + getSecondSurname();
        return fullName;
    }
}

