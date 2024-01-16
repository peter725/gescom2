package es.consumo.gescom.modules.arbitration.model.dto;

import java.io.Serializable;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
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
