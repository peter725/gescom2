package es.consumo.gescom.modules.campaign.service.impl;

import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class CampaignServiceImpl extends EntityCrudService<CampaignEntity, Long> implements CampaignService {
    protected CampaignServiceImpl(JJAARepository<CampaignEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public Page<CampaignEntity.SimpleProjection> findAllCampaignById(CriteriaWrapper<CampaignCriteria> wrapper, Long id) {
        return ((CampaignRepository) repository).findAllCampaignById(wrapper.getCriteria().toPageable(), id);
    }
}
