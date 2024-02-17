package es.consumo.gescom.modules.campaignProductService.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.campaignProductService.model.criteria.CampaignProductServiceCriteria;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CampaignProductServiceService extends CrudService<CampaignProductServiceEntity, Long>{

    Page<CampaignProductServiceEntity.SimpleProjection> findAllCampaignProductById(CriteriaWrapper<CampaignProductServiceCriteria> wrapper, Long id);

    List<CampaignProductServiceDTO> findCampaignProductServiceByCampaignId(Long id);
}
