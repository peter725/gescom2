package es.consumo.gescom.modules.specialist.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.specialist.model.criteria.SpecialistCriteria;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.springframework.data.domain.Page;

public interface SpecialistService extends CrudService<SpecialistEntity, Long>{

    Page<SpecialistEntity.SimpleProjection> findAllSpecialistById(CriteriaWrapper<SpecialistCriteria> wrapper, Long id);
    
}
