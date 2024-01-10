package es.consumo.junta_arbitral.modules.autonomousCommunity.service.impl;

import es.consumo.junta_arbitral.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.junta_arbitral.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.junta_arbitral.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.junta_arbitral.modules.autonomousCommunity.service.AutonomousCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;


@Service
public class AutonomousCommunityServiceImpl extends EntityCrudService<AutonomousCommunityEntity, Long> implements AutonomousCommunityService {
    protected AutonomousCommunityServiceImpl(JJAARepository<AutonomousCommunityEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityRepository autonomousCommunityRepository;

    @Override
    public Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(CriteriaWrapper<AutonomousCommunityCriteria> wrapper, String name) {
        return ((AutonomousCommunityRepository) repository).findAllAutonomousCommunityByName(wrapper.getCriteria().toPageable(), name);
    }
}
