package es.consumo.gescom.modules.sumProtocol.model.dto;

import java.io.Serializable;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.autonomousCommunityCountry.repository.AutonomousCommunityCountryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SumProtocolDTO implements Serializable, LongIdModel{

    private Long id;
    private String name;
    private String AutonomousCommunityCountryCode;
    private String ProductServiceCode;
    private Long CampaignId;
    private String ProtocolCode;
    private Long ProductServiceId;
    private Long ProtocolId;

}
