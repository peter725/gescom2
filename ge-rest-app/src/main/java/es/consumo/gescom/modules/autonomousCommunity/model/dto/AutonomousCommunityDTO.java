package es.consumo.gescom.modules.autonomousCommunity.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutonomousCommunityDTO implements Serializable {

    private Long id;

    private String name;

    
}
