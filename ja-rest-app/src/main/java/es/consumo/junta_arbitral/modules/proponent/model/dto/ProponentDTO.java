package es.consumo.junta_arbitral.modules.proponent.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProponentDTO implements Serializable {

    private Long id;
    private String name;
    
}
