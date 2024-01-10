package es.consumo.junta_arbitral.modules.proponent.service;

import es.consumo.junta_arbitral.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.junta_arbitral.modules.proponent.model.entity.ProponentEntity;
import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;

public interface ProponentService extends CrudService<ProponentEntity, Long>{

    Page<ProponentEntity.SimpleProjection> findAllProponentById(CriteriaWrapper<ProponentCriteria> wrapper, Long id);
    
}
