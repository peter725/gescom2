package es.consumo.gescom.modules.totalProtocolResults.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.totalProtocolResults.model.criteria.TotalProtocolResultsCriteria;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import org.springframework.data.domain.Page;

public interface TotalProtocolResultsService extends CrudService<TotalProtocolResultsEntity, Long> {

    Page<TotalProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<TotalProtocolResultsCriteria> wrapper, Long id);
}
