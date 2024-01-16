package es.consumo.gescom.modules.arbitrationBoard.model.dto;

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
public class ArbitrationBoardDTO implements Serializable, LongIdModel {

    private Long id;
    private String name;
    private String email;
    private String phone;
}

