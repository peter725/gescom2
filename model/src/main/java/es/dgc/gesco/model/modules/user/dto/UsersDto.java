package es.dgc.gesco.model.modules.user.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDto implements LongIdModel {
    private Long id;
    private Integer state = EntityState.ON.getValue();

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String firstSurname;

    @NotNull
    @NotBlank
    @Email
    private String email;

    public String getFullName() {
        String fullName = getName() + " " + getFirstSurname();
        return fullName;
    }
}

