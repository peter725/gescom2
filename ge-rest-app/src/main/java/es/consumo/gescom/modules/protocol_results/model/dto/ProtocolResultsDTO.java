package es.consumo.gescom.modules.protocol_results.model.dto;

import java.io.Serializable;

import es.consumo.gescom.commons.dto.LongIdModel;
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
    private String AutonomousCommunityCountryCode;
    private String ProductServiceCode;
    private Long CampaignId;
    private String ProtocolCode;
    private Long ProductServiceId;
    private Long ProtocolId;

}
