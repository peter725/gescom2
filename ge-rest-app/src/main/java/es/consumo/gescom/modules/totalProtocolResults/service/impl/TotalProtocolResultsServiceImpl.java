package es.consumo.gescom.modules.totalProtocolResults.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.converter.TotalProtocolResultsConverter;
import es.consumo.gescom.modules.totalProtocolResults.model.criteria.TotalProtocolResultsCriteria;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.repository.TotalProtocolResultsRepository;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TotalProtocolResultsServiceImpl extends EntityCrudService<TotalProtocolResultsEntity, Long> implements TotalProtocolResultsService {

    protected TotalProtocolResultsServiceImpl(GESCOMRepository<TotalProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private TotalProtocolResultsRepository totalProtocolResultsRepository;

    @Autowired
    private TotalProtocolResultsConverter totalProtocolResultsConverter;

    @Override
    public Page<TotalProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<TotalProtocolResultsCriteria> wrapper, Long id) {
        return ((TotalProtocolResultsRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public List<TotalProtocolResultsDTO> findByProtocolResultsId(ProtocolResultsDTO protocolResultsDTO) {
        List<TotalProtocolResultsEntity> totalProtocolResultsEntities = new ArrayList<>();
        List<TotalProtocolResultsDTO> totalProtocolResultsDTOS = new ArrayList<>();
        if (protocolResultsDTO.getCode() == null){
            totalProtocolResultsEntities = totalProtocolResultsRepository.findAllByProtocolResultsId(protocolResultsDTO.getId());

        }else {
            totalProtocolResultsEntities = totalProtocolResultsRepository.findAllByProtocolResultsCode(protocolResultsDTO.getCode());

        }
        totalProtocolResultsDTOS = totalProtocolResultsConverter.convertToModel(totalProtocolResultsEntities);

        return totalProtocolResultsDTOS;
    }


}
