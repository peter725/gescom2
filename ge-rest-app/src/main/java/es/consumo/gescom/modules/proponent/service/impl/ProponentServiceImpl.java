package es.consumo.gescom.modules.proponent.service.impl;

import es.consumo.gescom.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import es.consumo.gescom.modules.proponent.repository.ProponentRepository;
import es.consumo.gescom.modules.proponent.service.ProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class ProponentServiceImpl extends EntityCrudService<ProponentEntity, Long> implements ProponentService {
    protected ProponentServiceImpl(GESCOMRepository<ProponentEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProponentRepository proponentRepository;

    @Override
    public Page<ProponentEntity.SimpleProjection> findAllProponentById(CriteriaWrapper<ProponentCriteria> wrapper, Long id) {
        return ((ProponentRepository) repository).findAllProponentById(wrapper.getCriteria().toPageable(), id);
    }
}
