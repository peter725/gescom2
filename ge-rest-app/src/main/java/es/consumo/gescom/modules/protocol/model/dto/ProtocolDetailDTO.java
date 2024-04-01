package es.consumo.gescom.modules.protocol.model.dto;

import es.consumo.gescom.modules.questions.model.dto.QuestionDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolDetailDTO implements Serializable {

    private String campaignName;
    private String year;
    private String typeCampaign;
    private String ambit;
    private String responsible;
    private String participants;
    private String codeCPA;
    private String protocolName;
    private List<QuestionDetailDTO> questions;
}
