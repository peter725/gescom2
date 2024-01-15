package es.consumo.gescom.modules.permission.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import es.consumo.gescom.modules.permission.repository.PermissionRepository;
import es.consumo.gescom.modules.permission.service.PermissionService;

@Service
public class PermissionServiceImpl extends EntityCrudService<PermissionEntity, Long> implements PermissionService {

    protected PermissionServiceImpl(JJAARepository<PermissionEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<PermissionEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<PermissionCriteria> wrapper) {
        return ((PermissionRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }
    
}