package es.consumo.gescom.modules.phase.service;

import es.consumo.gescom.modules.phase.model.criteria.PhaseCriteria;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface PhaseService extends CrudService<PhaseEntity, Long>{

    Page<PhaseEntity.SimpleProjection> findAllPhaseById(CriteriaWrapper<PhaseCriteria> wrapper, Long id);
    
}
