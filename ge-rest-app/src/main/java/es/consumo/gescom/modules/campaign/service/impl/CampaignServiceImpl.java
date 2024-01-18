package es.consumo.gescom.modules.campaign.service.impl;

import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class CampaignServiceImpl extends EntityCrudService<CampaignEntity, Long> implements CampaignService {
    protected CampaignServiceImpl(GESCOMRepository<CampaignEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignRepository campaignRepository;


}
