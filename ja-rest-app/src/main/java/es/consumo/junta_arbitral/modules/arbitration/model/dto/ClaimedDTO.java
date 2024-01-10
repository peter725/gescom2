package es.consumo.junta_arbitral.modules.arbitration.model.dto;

import es.consumo.junta_arbitral.commons.dto.LongIdModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClaimedDTO implements Serializable, LongIdModel {

    private Long id;
    private String socialReason;
    private String nif;
    private String phone;
    private String email;
    private LocationDTO location;
}
