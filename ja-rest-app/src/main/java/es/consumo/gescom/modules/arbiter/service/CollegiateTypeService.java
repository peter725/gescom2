package es.consumo.gescom.modules.arbiter.service;

import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbiter.model.criteria.CollegiateTypeCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.CollegiateTypeEntity;

public interface CollegiateTypeService {

    Page<CollegiateTypeEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<CollegiateTypeCriteria> wrapper);

}

