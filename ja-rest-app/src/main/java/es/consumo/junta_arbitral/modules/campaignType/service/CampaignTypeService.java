package es.consumo.junta_arbitral.modules.campaignType.service;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.campaignType.model.criteria.CampaignTypeCriteria;
import es.consumo.junta_arbitral.modules.campaignType.model.entity.CampaignTypeEntity;
import org.springframework.data.domain.Page;

public interface CampaignTypeService extends CrudService<CampaignTypeEntity, Long>{

    Page<CampaignTypeEntity.SimpleProjection> findAllCampaignTypeEntityById(CriteriaWrapper<CampaignTypeCriteria> campaignTypeCriteriaCriteriaWrapper, Long id);
}
