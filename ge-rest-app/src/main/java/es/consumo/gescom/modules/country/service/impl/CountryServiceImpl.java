package es.consumo.gescom.modules.country.service.impl;

import es.consumo.gescom.modules.country.model.criteria.CountryCriteria;
import es.consumo.gescom.modules.country.model.entity.CountryEntity;
import es.consumo.gescom.modules.country.repository.CountryRepository;
import es.consumo.gescom.modules.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class CountryServiceImpl extends EntityCrudService<CountryEntity, Long> implements CountryService {
    protected CountryServiceImpl(GESCOMRepository<CountryEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CountryRepository ambitRepository;

    @Override
    public Page<CountryEntity.SimpleProjection> findAllCountryById(CriteriaWrapper<CountryCriteria> wrapper, Long id) {
        return ((CountryRepository) repository).findAllCountryById(wrapper.getCriteria().toPageable(), id);
    }
}
