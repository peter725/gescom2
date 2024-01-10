package es.consumo.junta_arbitral.modules.campaignType.service.impl;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.modules.campaignType.model.criteria.CampaignTypeCriteria;
import es.consumo.junta_arbitral.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.junta_arbitral.modules.campaignType.repository.CampaignTypeRepository;
import es.consumo.junta_arbitral.modules.campaignType.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class CampaignTypeServiceImpl extends EntityCrudService<CampaignTypeEntity, Long> implements CampaignTypeService {
    protected CampaignTypeServiceImpl(JJAARepository<CampaignTypeEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignTypeRepository campaignTypeRepository;

    public Page<CampaignTypeEntity.SimpleProjection> findAllCampaignTypeEntityById(CriteriaWrapper<CampaignTypeCriteria> wrapper, Long id) {
        return ((CampaignTypeRepository) repository).findAllCampaignTypeEntityById(wrapper.getCriteria().toPageable(), id);
    }
}

