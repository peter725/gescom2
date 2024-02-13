package es.consumo.gescom.modules.protocolServices.model.dto;

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
public class ProductServiceDTO implements Serializable, LongIdModel{

    private Long id;
    private String name;
    
}
