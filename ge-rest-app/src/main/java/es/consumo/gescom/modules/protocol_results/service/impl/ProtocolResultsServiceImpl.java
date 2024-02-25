package es.consumo.gescom.modules.protocol_results.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProtocolResultsServiceImpl extends EntityCrudService<ProtocolResultsEntity, Long> implements ProtocolResultsService {
    protected ProtocolResultsServiceImpl(GESCOMRepository<ProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Override
    public Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<ProtocolResultsCriteria> wrapper, Long id) {
        return ((ProtocolResultsRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }
}
