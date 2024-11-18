package es.consumo.gescom.modules.arbitration.model.dto;

import es.consumo.gescom.modules.arbitration.model.constants.ClaimantType;
import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClaimantDTO implements Serializable, LongIdModel {

    private Long id;
    private ClaimantType claimantType;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private String email;
    private LocationDTO location;
    private RepresentedDTO represented;
}
