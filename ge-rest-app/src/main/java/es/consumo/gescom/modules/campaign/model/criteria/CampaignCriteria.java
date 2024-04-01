package es.consumo.gescom.modules.campaign.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignCriteria extends FilterCriteria {
//    private Long id;
    private Long year;
//    private Long yearLong;
//    private String codeCpa;
    private String nameCampaign;
    private String campaignType;
    private String ambit;
//    private AutonomousCommunityCriteria autonomousCommunityResponsible;
//    private List<AutonomousCommunityParticipantsCriteria> participants;
//    private List<ProponentCriteria> proponents;
//    private List<SpecialistCriteria> specialists;
    private String phaseCampaign;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
