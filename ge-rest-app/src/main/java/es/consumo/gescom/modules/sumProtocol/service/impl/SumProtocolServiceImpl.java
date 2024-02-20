package es.consumo.gescom.modules.sumProtocol.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.sumProtocol.model.criteria.SumProtocolCriteria;
import es.consumo.gescom.modules.sumProtocol.model.entity.SumProtocolEntity;
import es.consumo.gescom.modules.sumProtocol.repository.SumProtocolRepository;
import es.consumo.gescom.modules.sumProtocol.service.SumProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class SumProtocolServiceImpl extends EntityCrudService<SumProtocolEntity, Long> implements SumProtocolService {
    protected SumProtocolServiceImpl(GESCOMRepository<SumProtocolEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private SumProtocolRepository sumProtocolRepository;

    @Override
    public Page<SumProtocolEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<SumProtocolCriteria> wrapper, Long id) {
        return ((SumProtocolRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }
}
