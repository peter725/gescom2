package es.consumo.gescom.modules.phase.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.phase.model.criteria.PhaseCriteria;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import es.consumo.gescom.modules.phase.repository.PhaseRepository;
import es.consumo.gescom.modules.phase.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class PhaseServiceImpl extends EntityCrudService<PhaseEntity, Long> implements PhaseService {
    protected PhaseServiceImpl(GESCOMRepository<PhaseEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private PhaseRepository ambitRepository;

    @Override
    public Page<PhaseEntity.SimpleProjection> findAllPhaseById(CriteriaWrapper<PhaseCriteria> wrapper, Long id) {
        return ((PhaseRepository) repository).findAllPhaseById(wrapper.getCriteria().toPageable(), id);
    }
}
