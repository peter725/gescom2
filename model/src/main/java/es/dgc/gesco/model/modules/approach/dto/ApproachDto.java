package es.dgc.gesco.model.modules.approach.dto;

import es.dgc.gesco.model.commom.constants.EntityState;
import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApproachDto implements LongIdModel {

    private Long id;
    private Integer state = EntityState.ON.getValue();

}