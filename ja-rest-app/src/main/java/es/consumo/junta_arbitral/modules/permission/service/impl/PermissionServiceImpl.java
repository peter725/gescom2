package es.consumo.junta_arbitral.modules.permission.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.junta_arbitral.modules.permission.model.entity.PermissionEntity;
import es.consumo.junta_arbitral.modules.permission.repository.PermissionRepository;
import es.consumo.junta_arbitral.modules.permission.service.PermissionService;

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