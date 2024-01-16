package es.consumo.gescom.modules.campaignProposal.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignProposalDTO implements Serializable {

    private Long id;

    private Boolean sent;

    private LocalDate date;

    private Long autonomousCommunityId;

    private String autonomousCommunityName;

    private Long userId;

    private String approach;

    private Long campaignTypeId;

    private String campaignTypeName;

    private String justification;

    private String objective;

    private String viability;

   // private Integer state = EntityState.ON.getValue();
    
}
