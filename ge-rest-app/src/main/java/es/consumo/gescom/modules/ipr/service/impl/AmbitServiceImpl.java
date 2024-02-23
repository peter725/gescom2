package es.consumo.gescom.modules.ipr.service.impl;

import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.ambit.repository.AmbitRepository;
import es.consumo.gescom.modules.ambit.service.AmbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class AmbitServiceImpl extends EntityCrudService<AmbitEntity, Long> implements AmbitService {
    protected AmbitServiceImpl(GESCOMRepository<AmbitEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AmbitRepository ambitRepository;

    @Override
    public Page<AmbitEntity.SimpleProjection> findAllAmbitById(CriteriaWrapper<AmbitCriteria> wrapper, Long id) {
        return ((AmbitRepository) repository).findAllAmbitById(wrapper.getCriteria().toPageable(), id);
    }
}
