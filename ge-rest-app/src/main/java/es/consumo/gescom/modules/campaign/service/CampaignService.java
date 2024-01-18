package es.consumo.gescom.modules.campaign.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;

import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface CampaignService extends CrudService<CampaignEntity, Long>{

}
