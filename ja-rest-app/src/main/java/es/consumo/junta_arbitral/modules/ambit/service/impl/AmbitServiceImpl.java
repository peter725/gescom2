package es.consumo.junta_arbitral.modules.ambit.service.impl;

import es.consumo.junta_arbitral.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.junta_arbitral.modules.ambit.model.entity.AmbitEntity;
import es.consumo.junta_arbitral.modules.ambit.repository.AmbitRepository;
import es.consumo.junta_arbitral.modules.ambit.service.AmbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;


@Service
public class AmbitServiceImpl extends EntityCrudService<AmbitEntity, Long> implements AmbitService {
    protected AmbitServiceImpl(JJAARepository<AmbitEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AmbitRepository ambitRepository;

    @Override
    public Page<AmbitEntity.SimpleProjection> findAllAmbitById(CriteriaWrapper<AmbitCriteria> wrapper, Long id) {
        return ((AmbitRepository) repository).findAllAmbitById(wrapper.getCriteria().toPageable(), id);
    }
}
