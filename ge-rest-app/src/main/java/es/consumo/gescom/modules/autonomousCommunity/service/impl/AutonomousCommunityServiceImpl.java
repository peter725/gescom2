package es.consumo.gescom.modules.autonomousCommunity.service.impl;

import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.proponent.model.converter.ProponentConverter;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

}
