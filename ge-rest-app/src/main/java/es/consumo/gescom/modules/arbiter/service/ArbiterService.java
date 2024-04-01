package es.consumo.gescom.modules.arbiter.service;


import es.consumo.gescom.modules.arbiter.model.criteria.ArbiterCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface ArbiterService extends CrudService<ArbiterEntity, Long> {

    Page<ArbiterEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbiterCriteria> wrapper);

    boolean existsByName(Long id, String name);
}
