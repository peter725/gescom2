package es.consumo.gescom.modules.arbitrationBoard.service;

import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface ArbitrationBoardService extends CrudService<ArbitrationBoardEntity, Long> {

    Page<ArbitrationBoardEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbitrationBoardCriteria> wrapper);

    boolean existsByName(Long id, String name);
}
