package es.consumo.gescom.modules.phase.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhaseDTO implements Serializable {

    private Long id;
    private String name;
    
}
