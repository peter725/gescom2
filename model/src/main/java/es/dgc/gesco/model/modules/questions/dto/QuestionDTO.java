package es.dgc.gesco.model.modules.questions.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO implements LongIdModel {

    Long id;

    String code;

    String protocol;

    String bkTrnrtipp;

    String bkTrcotipp;

    String question;

    String infringement;

    String bkTrinti;

    String bkTrinre;

    String bkTrrees;

}