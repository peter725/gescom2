package es.consumo.junta_arbitral.modules.module.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.modules.module.model.criteria.ModuleCriteria;
import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;
import es.consumo.junta_arbitral.modules.module.repository.ModuleRepository;
import es.consumo.junta_arbitral.modules.module.service.ModuleService;

@Service
public class ModuleServiceImpl extends EntityCrudService<ModuleEntity, Long> implements ModuleService  {

    protected ModuleServiceImpl(JJAARepository<ModuleEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<ModuleEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ModuleCriteria> wrapper) {
        return ((ModuleRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

}
