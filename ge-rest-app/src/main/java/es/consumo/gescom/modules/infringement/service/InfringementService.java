package es.consumo.gescom.modules.infringement.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.infringement.model.criteria.InfringementCriteria;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import org.springframework.data.domain.Page;

public interface InfringementService extends CrudService<InfringementEntity, Long>{

    Page<InfringementEntity.SimpleProjection> findAllInfringementEntityById(CriteriaWrapper<InfringementCriteria> campaignTypeCriteriaCriteriaWrapper, Long id);
}
