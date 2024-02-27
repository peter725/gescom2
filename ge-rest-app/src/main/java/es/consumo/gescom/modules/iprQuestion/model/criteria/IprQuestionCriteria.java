package es.consumo.gescom.modules.iprQuestion.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IprQuestionCriteria extends FilterCriteria {

    private Long id;
    private String code;
    private String iprCode;
    private Integer orderQuestion;
    private Integer percentageRespectTo;
    private String formula;
    private String question;
    private Long iprId;
}
