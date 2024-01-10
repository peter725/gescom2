package es.consumo.junta_arbitral.modules.arbiter.model.dto;

import es.consumo.junta_arbitral.commons.dto.LongIdModel;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.ArbiterTypeEntity;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.CollegiateTypeEntity;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArbiterDTO implements Serializable, LongIdModel {

    private Long id;
    private String name;
    private String surnames;
    private String nif;
    private String email;
    private ArbitrationBoardEntity arbitrationBoard;
    private ArbiterTypeEntity arbiterType;
    private CollegiateTypeEntity collegiateType;
    private Boolean deleted = false;


}

