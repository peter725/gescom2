package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.campaignType.dto.criteria.CampaignTypeCriteria;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.criteria.CampaignCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignTypeRepository extends BaseRepository<CampaignType, Long>, QueryByCriteria<CampaignType, CampaignTypeCriteria> {
}
