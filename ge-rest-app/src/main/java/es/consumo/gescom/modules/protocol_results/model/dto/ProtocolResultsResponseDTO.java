package es.consumo.gescom.modules.protocol_results.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolResultsResponseDTO implements Serializable, LongIdModel {

    //pr es la abreviatura de ProtocolResults

    private Long id;
    private String autonomousCommunityCountryCode;
    private String productServiceCode;
    private Long campaignId;
    private String protocolCode;
    private Long productServiceId;
    private Long protocolId;
    private Long autonomousCommunityCountryId;
    private String code;

    //tpr es la abreviatura de TotalProtocolResults

    private Long ccaaRen;
    private Long ccaaRep;
    private Long ccaaRes;
    private String codeQuestion;
    private Long protocolResultsId;



}
