package es.consumo.gescom.modules.autonomousCommunityCountry.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutonomousCommunityCountryDTO implements Serializable {

    private Long id;
    private String name;
    private String code;
    private String countryCode;
    private Long countryId;
    
}
