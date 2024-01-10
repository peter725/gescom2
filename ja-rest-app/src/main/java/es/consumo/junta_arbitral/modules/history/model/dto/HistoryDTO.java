package es.consumo.junta_arbitral.modules.history.model.dto;

import java.io.Serializable;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO implements Serializable {

    private Long id;
    private ArbitrationEntity arbitrationEntity;
    private String description;
    
}
