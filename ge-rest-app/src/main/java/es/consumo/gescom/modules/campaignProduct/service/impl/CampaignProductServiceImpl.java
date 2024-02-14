package es.consumo.gescom.modules.campaignProduct.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.campaignProduct.model.criteria.CampaignProductCriteria;
import es.consumo.gescom.modules.campaignProduct.model.entity.CampaignProductEntity;
import es.consumo.gescom.modules.campaignProduct.repository.CampaignProductRepository;
import es.consumo.gescom.modules.campaignProduct.service.CampaignProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductServiceImpl extends EntityCrudService<CampaignProductEntity, Long> implements CampaignProductService {
    protected CampaignProductServiceImpl(GESCOMRepository<CampaignProductEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignProductRepository campaignProductRepository;


    public Page<CampaignProductEntity.SimpleProjection> findAllCampaignProductById(CriteriaWrapper<CampaignProductCriteria> wrapper, Long id) {
        return ((CampaignProductRepository) repository).findAllCampaignProductById(wrapper.getCriteria().toPageable(), id);
    }
}
