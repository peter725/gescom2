package es.consumo.gescom.modules.authorityDGC.model.dto;

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
public class AuthorityDGCDTO implements Serializable, LongIdModel{

    private Long id;
    private String name;
    
}
