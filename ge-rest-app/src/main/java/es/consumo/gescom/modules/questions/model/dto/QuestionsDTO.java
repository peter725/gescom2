package es.consumo.gescom.modules.questions.model.dto;

import java.io.Serializable;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsDTO implements Serializable, LongIdModel{

    private Long id;
    private String code;
    private Long protocolCampaignId;
    private Integer orderQuestion;
    private String codeQuestion;
    private String question;
    private String codeInfringement;
    private String bkTrinti;
    private String response;
    private String bkTrrees;
    private String protocolCampaignCode;

}
