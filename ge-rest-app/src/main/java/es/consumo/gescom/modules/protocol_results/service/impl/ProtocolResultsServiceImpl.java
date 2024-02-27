package es.consumo.gescom.modules.protocol_results.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.ipr.model.converter.IprConverter;
import es.consumo.gescom.modules.protocol_results.model.converter.ProtocolResultsConverter;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.repository.TotalProtocolResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProtocolResultsServiceImpl extends EntityCrudService<ProtocolResultsEntity, Long> implements ProtocolResultsService {
    protected ProtocolResultsServiceImpl(GESCOMRepository<ProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Autowired
    private TotalProtocolResultsRepository totalProtocolResultsRepository;

    @Autowired
    private ProtocolResultsConverter protocolResultsConverter;

    @Override
    public Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<ProtocolResultsCriteria> wrapper, Long id) {
        return ((ProtocolResultsRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public ProtocolResultsEntity saveProtocolResults(ProtocolResultsDTO protocolResults) {
        ProtocolResultsEntity protocolResultsEntity = protocolResultsConverter.convertToEntity(protocolResults);
        ProtocolResultsEntity protocolResultsEntitySave = protocolResultsRepository.save(protocolResultsEntity);

        List<TotalProtocolResultsDTO> totalProtocolResultsDTOS = protocolResults.getTotalProtocolResults();
        totalProtocolResultsDTOS.forEach(totalProtocolResults -> {
            TotalProtocolResultsEntity totalProtocolResultsEntity = new TotalProtocolResultsEntity();
            totalProtocolResultsEntity.setCcaaRen(totalProtocolResults.getCcaa_ren());
            totalProtocolResultsEntity.setCcaaRep(totalProtocolResults.getCcaa_rep());
            totalProtocolResultsEntity.setCcaaRes(totalProtocolResults.getCcaa_res());
            totalProtocolResultsEntity.setCode(totalProtocolResults.getCode());
            totalProtocolResultsEntity.setProtocolResultsCode(totalProtocolResults.getProtocolResultsCode());
            totalProtocolResultsEntity.setCodeQuestion(totalProtocolResults.getCodeQuestion());
            totalProtocolResultsEntity.setProtocolResultsId(protocolResultsEntitySave.getId());

            totalProtocolResultsRepository.save(totalProtocolResultsEntity);
        });

        return protocolResultsEntitySave;
    }
}
