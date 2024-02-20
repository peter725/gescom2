package es.consumo.gescom.modules.sumProtocol.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.sumProtocol.model.criteria.SumProtocolCriteria;
import es.consumo.gescom.modules.sumProtocol.model.entity.SumProtocolEntity;
import org.springframework.data.domain.Page;

public interface SumProtocolService extends CrudService<SumProtocolEntity, Long>{

    Page<SumProtocolEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<SumProtocolCriteria> wrapper, Long id);
    
}
