package es.consumo.junta_arbitral.modules.module.model.dto;

import java.io.Serializable;

import es.consumo.junta_arbitral.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO implements Serializable, LongIdModel  {
    private Long id;
    private String name;
    private String code;
    private Boolean visible;
}
