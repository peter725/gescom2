package es.consumo.gescom.modules.autonomousCommunityProponent.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.autonomousCommunityProponent.repository.AutonomousCommunityProponentRepository;
import es.consumo.gescom.modules.autonomousCommunityProponent.service.AutonomousCommunityProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class AutonomousCommunityProponentServiceImpl extends EntityCrudService<AutonomousCommunityProponentEntity, Long> implements AutonomousCommunityProponentService {
    protected AutonomousCommunityProponentServiceImpl(GESCOMRepository<AutonomousCommunityProponentEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityProponentRepository autonomousCommunityRepository;

}
