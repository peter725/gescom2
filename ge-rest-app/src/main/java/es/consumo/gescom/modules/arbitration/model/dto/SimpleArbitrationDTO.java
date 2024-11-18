package es.consumo.gescom.modules.arbitration.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleArbitrationDTO implements Serializable, LongIdModel {

    private Long id;
    private String createAt;
    private String claimantName;
    private String claimantSurnames;
    private String claimantNif;
    private String boardTypeName;
    private String notificationTypeName;
    private String claimedNif;
    private String claimedSocialReason;
    private String status;
}

