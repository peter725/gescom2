package es.consumo.junta_arbitral.modules.history.service;

import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.history.model.criteria.HistoryCriteria;
import es.consumo.junta_arbitral.modules.history.model.entity.HistoryEntity;

public interface HistoryService extends CrudService<HistoryEntity, Long>{

    Page<HistoryEntity.SimpleProjection> findAllHistoryByArbitration(CriteriaWrapper<HistoryCriteria> wrapper, Long id);
    
}
