package es.consumo.junta_arbitral.modules.arbiter.service;


import es.consumo.junta_arbitral.modules.arbiter.model.criteria.ArbiterCriteria;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface ArbiterService extends CrudService<ArbiterEntity, Long> {

    Page<ArbiterEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbiterCriteria> wrapper);

    boolean existsByName(Long id, String name);
}
