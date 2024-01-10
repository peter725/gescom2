package es.consumo.junta_arbitral.modules.ambit.service;

import es.consumo.junta_arbitral.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.junta_arbitral.modules.ambit.model.entity.AmbitEntity;
import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;

public interface AmbitService extends CrudService<AmbitEntity, Long>{

    Page<AmbitEntity.SimpleProjection> findAllAmbitById(CriteriaWrapper<AmbitCriteria> wrapper, Long id);
    
}
