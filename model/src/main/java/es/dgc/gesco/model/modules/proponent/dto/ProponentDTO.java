package es.dgc.gesco.model.modules.proponent.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProponentDTO implements LongIdModel {

    @EqualsAndHashCode.Include
    Long id;

    String name;
}