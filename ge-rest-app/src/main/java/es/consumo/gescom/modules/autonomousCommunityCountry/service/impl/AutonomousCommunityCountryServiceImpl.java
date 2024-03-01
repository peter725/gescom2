package es.consumo.gescom.modules.autonomousCommunityCountry.service.impl;

import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.converter.AutonomousCommunityCountryConverter;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.criteria.AutonomousCommunityCountryCriteria;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.dto.AutonomousCommunityCountryDTO;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.entity.AutonomousCommunityCountryEntity;
import es.consumo.gescom.modules.autonomousCommunityCountry.repository.AutonomousCommunityCountryRepository;
import es.consumo.gescom.modules.autonomousCommunityCountry.service.AutonomousCommunityCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class AutonomousCommunityCountryServiceImpl extends EntityCrudService<AutonomousCommunityCountryEntity, Long> implements AutonomousCommunityCountryService {
    protected AutonomousCommunityCountryServiceImpl(GESCOMRepository<AutonomousCommunityCountryEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityCountryRepository autonomousCommunityRepository;

    @Autowired
    private AutonomousCommunityCountryConverter autonomousCommunityConverter;

    @Override
    public Page<AutonomousCommunityCountryEntity.SimpleProjection> findAllAutonomousCommunityCountryByName(CriteriaWrapper<AutonomousCommunityCountryCriteria> wrapper, Long id) {
        return ((AutonomousCommunityCountryRepository) repository).findAllAutonomousCommunityCountryByName(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public AutonomousCommunityCountryDTO findByCode(String code) {
        return autonomousCommunityConverter.convertToModel(autonomousCommunityRepository.findByCode(code));
    }

    @Override
    public AutonomousCommunityCountryDTO findCCAAById(Long id) {
        return autonomousCommunityConverter.convertToModel(autonomousCommunityRepository.findCCAAById(id));
    }

}
