package es.dgc.gesco.model.modules.approach.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApproachDto implements LongIdModel {

    private Long id;
    private Integer state = EntityState.ON.getValue();

    @NotNull
    @NotBlank
    private Boolean sent;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String autonomousCommunity;

    @NotNull
    @NotBlank
    private Long userId;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String approach;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String campaignType;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String justification;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String objective;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String viability;
}