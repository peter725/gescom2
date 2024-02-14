package es.consumo.gescom.modules.campaignProduct.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.campaignProduct.model.criteria.CampaignProductCriteria;
import es.consumo.gescom.modules.campaignProduct.model.entity.CampaignProductEntity;
import org.springframework.data.domain.Page;

public interface CampaignProductService extends CrudService<CampaignProductEntity, Long>{

    Page<CampaignProductEntity.SimpleProjection> findAllCampaignProductById(CriteriaWrapper<CampaignProductCriteria> wrapper, Long id);
    
}
