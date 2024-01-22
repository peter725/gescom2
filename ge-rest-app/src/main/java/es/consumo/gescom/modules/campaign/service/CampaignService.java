package es.consumo.gescom.modules.campaign.service;

import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;

import es.consumo.gescom.commons.service.CrudService;

public interface CampaignService extends CrudService<CampaignEntity, Long>{

    CampaignDTO createCampaign(CampaignDTO campaignDTO);

    CampaignDTO updateCampaign(Long id, CampaignDTO campaignDTO);

    CampaignDTO findCampaignById(Long idCampaignDTO);
}
