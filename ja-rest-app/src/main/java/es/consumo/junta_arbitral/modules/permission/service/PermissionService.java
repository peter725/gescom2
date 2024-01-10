package es.consumo.junta_arbitral.modules.permission.service;

import es.consumo.junta_arbitral.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.junta_arbitral.modules.permission.model.entity.PermissionEntity;

import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;

public interface PermissionService extends CrudService<PermissionEntity, Long> {

    Page<PermissionEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<PermissionCriteria> wrapper);
    
}
