package es.consumo.gescom.modules.ipr.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.SearchDTO;
import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IprService extends CrudService<IprEntity, Long>{

    Page<IprEntity.SimpleProjection> findAllIprById(CriteriaWrapper<IprCriteria> wrapper, Long id);

    IprDTO findIprDTOById(Long id);

    IprDTO createIPR(IprDTO payload);

    List<IprDTO> findAllIprByCampaignIdAndProtocolCode(Long campaignId, String protocolCode);

    List<IprDTO> findAllIprByCampaignIdAndProtocolId(Long campaignId, Long protocolId);

    ResultsResponseDTO getResultsIpr(SearchDTO searchDTO);

    ResultsResponseDTO getResultProtocol(SearchDTO searchDTO);
    
    IprDTO updateIpr(Long id, IprDTO iprDTO);
    
    List<IprDTO> findAllIprByCampaignId(Long campaignId);

    List<IprQuestionDTO> getAllQuestionsByIprId(Long Id);

    List<IprQuestionDTO> getAllQuestionsByIprCode(String code);

}
