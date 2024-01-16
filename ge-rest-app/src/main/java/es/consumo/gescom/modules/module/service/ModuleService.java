package es.consumo.gescom.modules.module.service;

import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.module.model.criteria.ModuleCriteria;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;

public interface ModuleService extends CrudService<ModuleEntity, Long> {

    Page<ModuleEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ModuleCriteria> wrapper);
}
