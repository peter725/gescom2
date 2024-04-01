package es.consumo.gescom.modules.iprQuestion.model.dto;

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
public class IprQuestionDTO implements Serializable, LongIdModel{

    private Long id;
    private String code;
    private String iprCode;
    private Integer orderQuestion;
    private Integer percentageRespectTo;
    private String formula;
    private String question;
    private Long iprId;
    
}
