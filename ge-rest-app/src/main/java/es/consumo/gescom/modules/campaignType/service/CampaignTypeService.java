package es.consumo.gescom.modules.campaignType.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.campaignType.model.criteria.CampaignTypeCriteria;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import org.springframework.data.domain.Page;

public interface CampaignTypeService extends CrudService<CampaignTypeEntity, Long>{

    Page<CampaignTypeEntity.SimpleProjection> findAllCampaignTypeEntityById(CriteriaWrapper<CampaignTypeCriteria> campaignTypeCriteriaCriteriaWrapper, Long id);
}
