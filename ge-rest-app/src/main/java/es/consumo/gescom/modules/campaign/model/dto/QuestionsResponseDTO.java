package es.consumo.gescom.modules.campaign.model.dto;

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
public class QuestionsResponseDTO implements Serializable, LongIdModel {

    private Long id;
    private String codeQuestion;
    private Integer orderQuestion;
    private String question;
    private Long total = 0L;
    private Float percentage;
    private Integer percentageRespectTo;

    // Método para acumular valores en el total
    public void addToTotal(Long value) {
        if (value != null){
            this.total += value;
        }else{
            this.total += 0;
        }
    }
}
