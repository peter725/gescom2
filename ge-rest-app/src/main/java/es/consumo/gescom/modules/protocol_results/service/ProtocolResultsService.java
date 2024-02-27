package es.consumo.gescom.modules.protocol_results.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProtocolResultsService extends CrudService<ProtocolResultsEntity, Long>{

    Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<ProtocolResultsCriteria> wrapper, Long id);

    ProtocolResultsEntity saveProtocolResults(ProtocolResultsDTO protocolResults);
    List<ProtocolResultsDTO> findProtocolResultsByCampaignId(Long campaignId);
}
