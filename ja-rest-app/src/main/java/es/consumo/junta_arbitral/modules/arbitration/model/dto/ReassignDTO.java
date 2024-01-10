package es.consumo.junta_arbitral.modules.arbitration.model.dto;

import java.io.Serializable;

import es.consumo.junta_arbitral.commons.dto.LongIdModel;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReassignDTO implements Serializable, LongIdModel {
    private Long id;
    private String causes;
    private ArbitrationBoardEntity arbitrationBoard;
}
