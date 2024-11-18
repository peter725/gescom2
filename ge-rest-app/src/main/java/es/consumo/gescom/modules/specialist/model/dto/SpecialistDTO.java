package es.consumo.gescom.modules.specialist.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO implements Serializable {

    private Long id;
    private String name;
    
}
