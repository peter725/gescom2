package es.consumo.gescom.modules.permission.service;

import es.consumo.gescom.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;

import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface PermissionService extends CrudService<PermissionEntity, Long> {

    Page<PermissionEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<PermissionCriteria> wrapper);
    
}
