package es.consumo.gescom.modules.country.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.country.model.criteria.CountryCriteria;
import es.consumo.gescom.modules.country.model.entity.CountryEntity;
import org.springframework.data.domain.Page;

public interface CountryService extends CrudService<CountryEntity, Long>{

    Page<CountryEntity.SimpleProjection> findAllCountryById(CriteriaWrapper<CountryCriteria> wrapper, Long id);
    
}
