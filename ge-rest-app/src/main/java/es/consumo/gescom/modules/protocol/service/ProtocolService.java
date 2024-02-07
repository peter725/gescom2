package es.consumo.gescom.modules.protocol.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface ProtocolService extends CrudService<ProtocolEntity, Long>{

    Page<ProtocolEntity> getProtocolByNameOrCode(CriteriaWrapper<ProtocolCriteria> protocolCriteriaCriteriaWrapper, String protocol, String code);

    Page<ProtocolEntity> findProtocolByCampaignId(CriteriaWrapper<ProtocolCriteria> protocolCriteriaCriteriaWrapper, Long idCampaign);

    ProtocolDTO createProtocol(ProtocolDTO payload);
}
