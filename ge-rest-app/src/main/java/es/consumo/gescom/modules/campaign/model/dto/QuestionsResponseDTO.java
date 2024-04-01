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

    private Long numResponseSi = 0L;
    private Long numResponseNo = 0L;
    private Long numResponseNoProcede = 0L;

    // Método para acumular valores en el total
    public void addToTotal(Long value) {
        if (value != null){
            this.total += value;
        }else{
            this.total += 0;
        }
    }

    // Método para acumular valores en numResponseSi
    public void addToNumResponseSi(Long value) {
        if (value != null){
            this.numResponseSi += value;
        }else{
            this.numResponseSi += 0;
        }
    }

    // Método para acumular valores en numResponseNo
    public void addToNumResponseNo(Long value) {
        if (value != null){
            this.numResponseNo += value;
        }else{
            this.numResponseNo += 0;
        }
    }

    // Método para acumular valores en numResponseNoProcede
    public void addToNumResponseNoProcede(Long value) {
        if (value != null){
            this.numResponseNoProcede += value;
        }else{
            this.numResponseNoProcede += 0;
        }
    }
}
