package es.consumo.gescom.modules.questions.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDetailDTO implements Serializable {

    private String question;
    private String infringement;
    private String response;

}

