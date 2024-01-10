package es.consumo.junta_arbitral.modules.arbiter.service;

import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.modules.arbiter.model.criteria.CollegiateTypeCriteria;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.CollegiateTypeEntity;

public interface CollegiateTypeService {

    Page<CollegiateTypeEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<CollegiateTypeCriteria> wrapper);

}

