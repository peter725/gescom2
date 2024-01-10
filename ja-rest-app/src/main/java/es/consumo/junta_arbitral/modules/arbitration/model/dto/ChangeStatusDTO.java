package es.consumo.junta_arbitral.modules.arbitration.model.dto;

import java.io.Serializable;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDTO implements Serializable {
    private Long arbitrationId;
    private String causes;
    private ArbitrationStatusEntity status;
}
