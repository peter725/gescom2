package es.consumo.gescom.modules.autonomousCommunity.service.impl;

import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.util.ArrayList;
import java.util.List;


@Service
public class AutonomousCommunityServiceImpl extends EntityCrudService<AutonomousCommunityEntity, Long> implements AutonomousCommunityService {
    protected AutonomousCommunityServiceImpl(GESCOMRepository<AutonomousCommunityEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityRepository autonomousCommunityRepository;

    @Autowired
    private AutonomousCommunityConverter autonomousCommunityConverter;

    @Override
    public Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(CriteriaWrapper<AutonomousCommunityCriteria> wrapper, String name) {
        return ((AutonomousCommunityRepository) repository).findAllAutonomousCommunityByName(wrapper.getCriteria().toPageable(), name);
    }

    @Override
    public Page<?> findAllCCAAActive(CriteriaWrapper<?> wrapper) {
        List<AutonomousCommunityEntity> autonomousCommunityEntities = new ArrayList<>();
        Page<AutonomousCommunityEntity> autonomousCommunityEntityPage = repository.findAll(wrapper.getCriteria().toPageable());
        autonomousCommunityEntityPage.forEach( autonomousCommunity -> {
            if(autonomousCommunity.getState().equals(1)){
                autonomousCommunityEntities.add(autonomousCommunity);
            }
        });

        return new PageImpl<>(autonomousCommunityEntities, autonomousCommunityEntityPage.getPageable(), autonomousCommunityEntityPage.getTotalElements());
    }

}
