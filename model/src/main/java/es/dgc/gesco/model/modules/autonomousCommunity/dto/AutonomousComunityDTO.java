package es.dgc.gesco.model.modules.autonomousCommunity.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AutonomousComunityDTO implements LongIdModel {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

}
