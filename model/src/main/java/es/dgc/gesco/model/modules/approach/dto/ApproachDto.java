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

    Long id;

    Boolean sent;

    String autonomousCommunity;

    Long userId;

    String approach;

    Long campaignTypeId;

    String campaignTypeName;

    String justification;

    String objective;

    String viability;
}