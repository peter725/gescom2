package es.consumo.gescom.modules.ipr.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IprDTO implements Serializable, LongIdModel{

    private Long id;
    private String name;
    private String code;
    //private String protocolCode;
    private Long campaignId;
    //private Long protocolId;
    private ProtocolDTO protocol;
    private List<IprQuestionDTO> iprQuestionDTOList;
    private ResultsResponseDTO resultsResponseDTO;

    private String nameCampaign;
    private String protocolName;
    //año de la campaña
    private Long year;
}
