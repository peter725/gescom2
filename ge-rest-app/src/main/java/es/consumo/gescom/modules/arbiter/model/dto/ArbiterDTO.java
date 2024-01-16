package es.consumo.gescom.modules.arbiter.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.arbiter.model.entity.ArbiterTypeEntity;
import es.consumo.gescom.modules.arbiter.model.entity.CollegiateTypeEntity;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

