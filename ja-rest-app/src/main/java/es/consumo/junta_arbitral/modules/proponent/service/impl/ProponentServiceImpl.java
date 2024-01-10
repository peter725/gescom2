package es.consumo.junta_arbitral.modules.proponent.service.impl;

import es.consumo.junta_arbitral.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.junta_arbitral.modules.proponent.model.entity.ProponentEntity;
import es.consumo.junta_arbitral.modules.proponent.repository.ProponentRepository;
import es.consumo.junta_arbitral.modules.proponent.service.ProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;


@Service
public class ProponentServiceImpl extends EntityCrudService<ProponentEntity, Long> implements ProponentService {
    protected ProponentServiceImpl(JJAARepository<ProponentEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProponentRepository proponentRepository;

    @Override
    public Page<ProponentEntity.SimpleProjection> findAllProponentById(CriteriaWrapper<ProponentCriteria> wrapper, Long id) {
        return ((ProponentRepository) repository).findAllProponentById(wrapper.getCriteria().toPageable(), id);
    }
}
