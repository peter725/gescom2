package es.dgc.gesco.model.modules.autonomousCommunity.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AutonomousComunityDTO implements LongIdModel {

    private Long id;
    private String name;

}
