package es.dgc.gesco.model.modules.user.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;
import es.dgc.gesco.model.commom.validation.constraints.NIF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 100)
    private String secondSurname;

    @NotNull
    @NotBlank
    @NIF
    private String nif;

    @NotNull
    @NotBlank
    private String position;



    @Email
    private List<Email> emailList;


    @NotBlank
    private Long phoneId;


    @NotNull
    @NotBlank
    private Long roleId;

    public String getFullName() {
        String fullName = getName() + " " + getFirstSurname() + " " + getSecondSurname();
        return fullName;
    }
}

