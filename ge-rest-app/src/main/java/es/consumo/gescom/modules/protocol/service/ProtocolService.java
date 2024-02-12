package es.consumo.gescom.modules.protocol.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDetailDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProtocolService extends CrudService<ProtocolEntity, Long>{

    Page<ProtocolEntity> getProtocolByNameOrCode(CriteriaWrapper<ProtocolCriteria> protocolCriteriaCriteriaWrapper, String protocol, String code);

    List<ProtocolDTO> findProtocolByCampaignId(Long idCampaign);

    //ProtocolDTO findProtocolDetailById(Long id);

    ProtocolDTO createProtocol(ProtocolDTO payload);

    ProtocolDetailDTO findProtocolById(Long id);

}
