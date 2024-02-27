package es.consumo.gescom.modules.protocol_results.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.protocol_results.model.converter.ProtocolResultsConverter;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProtocolResultsServiceImpl extends EntityCrudService<ProtocolResultsEntity, Long> implements ProtocolResultsService {
    protected ProtocolResultsServiceImpl(GESCOMRepository<ProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Autowired
    private TotalProtocolResultsService totalProtocolResultsService;

    @Autowired
    private ProtocolResultsConverter protocolResultsConverter;

    @Override
    public Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<ProtocolResultsCriteria> wrapper, Long id) {
        return ((ProtocolResultsRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public List<ProtocolResultsDTO> findProtocolResultsByCampaignId(Long campaignId) {
        List<ProtocolResultsEntity> protocolResultsEntities = protocolResultsRepository.findAllByCampaignId(campaignId);
        List<ProtocolResultsDTO> protocolResultsDTOS = protocolResultsConverter.convertToModel(protocolResultsEntities);
        for (ProtocolResultsDTO protocolResultsDTO : protocolResultsDTOS){
            protocolResultsDTO.setTotalProtocolResultsDTOS(totalProtocolResultsService.findByProtocolResultsId(protocolResultsDTO));

        }

        return protocolResultsDTOS;
    }


}
