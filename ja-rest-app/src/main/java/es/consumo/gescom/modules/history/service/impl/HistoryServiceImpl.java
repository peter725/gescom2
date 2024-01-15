package es.consumo.gescom.modules.history.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.history.model.criteria.HistoryCriteria;
import es.consumo.gescom.modules.history.model.entity.HistoryEntity;
import es.consumo.gescom.modules.history.repository.HistoryRepository;
import es.consumo.gescom.modules.history.service.HistoryService;

@Service
public class HistoryServiceImpl extends EntityCrudService<HistoryEntity, Long> implements HistoryService {
    protected HistoryServiceImpl(JJAARepository<HistoryEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ArbitrationRepository arbitrationRepository;

    @Override
    public Page<HistoryEntity.SimpleProjection> findAllHistoryByArbitration(CriteriaWrapper<HistoryCriteria> wrapper, Long id) {
        ArbitrationEntity arbitrationEntity = arbitrationRepository.findById(id).orElseThrow();
        return historyRepository.findByArbitrationId(wrapper.getCriteria().toPageable(), arbitrationEntity);
    }
}
