package es.consumo.gescom.modules.campaign.service;

import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;

import es.consumo.gescom.commons.service.CrudService;

public interface CampaignService extends CrudService<CampaignEntity, Long>{

    CampaignEntity createCampaign(CampaignDTO campaignDTO);

    CampaignEntity updateCampaign(CampaignDTO campaignDTO);

//    CampaignDTO findCampaignById(Long idCampaignDTO);
}
