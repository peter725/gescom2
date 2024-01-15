package es.consumo.gescom.modules.campaign.service;

import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface CampaignService extends CrudService<CampaignEntity, Long>{

    Page<CampaignEntity.SimpleProjection> findAllCampaignById(CriteriaWrapper<CampaignCriteria> wrapper, Long id);
    
}
