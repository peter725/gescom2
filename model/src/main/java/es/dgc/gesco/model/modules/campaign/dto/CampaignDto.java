package es.dgc.gesco.model.modules.campaign.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignDto implements LongIdModel {

    Long id;

    Long year;

    String cpa;

    String nameCampaign;

    CampaignType typeCampaignId;

    Ambit ambitId;

    AutonomousCommunity autonomousCommunityResponsibleId;

    PhaseCampaign phaseId;
}
