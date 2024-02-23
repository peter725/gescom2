package es.consumo.gescom.modules.ipr.service.impl;

import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class IprServiceImpl extends EntityCrudService<IprEntity, Long> implements IprService {
    protected IprServiceImpl(GESCOMRepository<IprEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private IprRepository iprRepository;

    @Override
    public Page<IprEntity.SimpleProjection> findAllIprById(CriteriaWrapper<IprCriteria> wrapper, Long id) {
        return ((IprRepository) repository).findAllIprById(wrapper.getCriteria().toPageable(), id);
    }
}
