package es.consumo.gescom.modules.autonomousCommunityProponent.model.dto;

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
public class AutonomousCommunityProponentDTO implements Serializable, LongIdModel {

    private Long id;
    private String name;
    
}
