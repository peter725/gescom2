package es.consumo.gescom.modules.module.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.module.model.criteria.ModuleCriteria;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.module.repository.ModuleRepository;
import es.consumo.gescom.modules.module.service.ModuleService;

@Service
public class ModuleServiceImpl extends EntityCrudService<ModuleEntity, Long> implements ModuleService  {

    protected ModuleServiceImpl(GESCOMRepository<ModuleEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<ModuleEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ModuleCriteria> wrapper) {
        return ((ModuleRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

}
