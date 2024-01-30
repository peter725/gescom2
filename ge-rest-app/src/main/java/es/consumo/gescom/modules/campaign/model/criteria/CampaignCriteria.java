package es.consumo.gescom.modules.campaign.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.criteria.AutonomousCommunityParticipantsCriteria;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.phase.model.criteria.PhaseCriteria;
import es.consumo.gescom.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.gescom.modules.specialist.model.criteria.SpecialistCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignCriteria extends FilterCriteria {
    private Long id;
    private Long year;
    private String codeCpa;
    private String nameCampaign;
    private CampaignTypeEntity campaignType;
    private AmbitEntity ambit;
    private AutonomousCommunityCriteria autonomousCommunityResponsible;
    private List<AutonomousCommunityParticipantsCriteria> participants;
    private List<ProponentCriteria> proponents;
    private List<SpecialistCriteria> specialists;
    private PhaseCriteria phaseCampaign;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
