package es.consumo.junta_arbitral.modules.module.service;

import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.module.model.criteria.ModuleCriteria;
import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;

public interface ModuleService extends CrudService<ModuleEntity, Long> {

    Page<ModuleEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ModuleCriteria> wrapper);
}
