package es.consumo.junta_arbitral.modules.arbitration.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.NotificationTypeEntity;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.junta_arbitral.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArbitrationDTO implements Serializable, LongIdModel {

    private Long id;
    private ClaimantDTO claimant;
    private ArbitrationBoardEntity arbitrationBoard;
    private NotificationTypeEntity notificationType;
    private ArbitrationStatusEntity status;
    private ClaimedDTO claimed;
    private String reason;
    private String aspiration;
    private String observation;
    private String causes;
}

