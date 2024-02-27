package es.consumo.gescom.modules.protocol_results.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.dto.LongIdModel;
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
    private String autonomousCommunityCountryCode;
    private String productServiceCode;
    private Long campaignId;
    private String protocolCode;
    private Long productServiceId;
    private Long protocolId;
    private List<TotalProtocolResultsDTO> totalProtocolResults;

}
