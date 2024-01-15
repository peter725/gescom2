package es.consumo.gescom.modules.history.service;

import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.history.model.criteria.HistoryCriteria;
import es.consumo.gescom.modules.history.model.entity.HistoryEntity;

public interface HistoryService extends CrudService<HistoryEntity, Long>{

    Page<HistoryEntity.SimpleProjection> findAllHistoryByArbitration(CriteriaWrapper<HistoryCriteria> wrapper, Long id);
    
}
