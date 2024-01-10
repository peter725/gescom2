package es.consumo.junta_arbitral.modules.arbitrationBoard.service;

import es.consumo.junta_arbitral.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface ArbitrationBoardService extends CrudService<ArbitrationBoardEntity, Long> {

    Page<ArbitrationBoardEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbitrationBoardCriteria> wrapper);

    boolean existsByName(Long id, String name);
}
