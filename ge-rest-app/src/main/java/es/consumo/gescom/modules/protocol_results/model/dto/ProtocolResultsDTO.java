package es.consumo.gescom.modules.protocol_results.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.dto.AutonomousCommunityCountryDTO;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolResultsDTO implements Serializable, LongIdModel{

    private Long id;

    private String name;

    private Long autonomousCommunityCountryId;
    private String autonomousCommunityCountryCode;
    private AutonomousCommunityCountryDTO autonomousCommunityCountryDTO;

    private Long productServiceId;
    private String productServiceCode;
    private ProductServiceDTO productServiceDTO;

    private Long campaignId;

    private Long protocolId;
    private String protocolCode;
    private ProtocolDTO protocolDTO;
    private String protocolName;

    private String Code;

    private List<TotalProtocolResultsDTO> totalProtocolResultsDTOS;

}
