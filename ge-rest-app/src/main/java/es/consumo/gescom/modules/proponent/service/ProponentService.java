package es.consumo.gescom.modules.proponent.service;

import es.consumo.gescom.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface ProponentService extends CrudService<ProponentEntity, Long>{

    Page<ProponentEntity.SimpleProjection> findAllProponentById(CriteriaWrapper<ProponentCriteria> wrapper, Long id);
    
}
