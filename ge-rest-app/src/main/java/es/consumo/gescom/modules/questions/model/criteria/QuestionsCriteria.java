package es.consumo.gescom.modules.questions.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionsCriteria extends FilterCriteria {
    private Long id;
    private String code;
    private ProtocolEntity protocolCampaingId;
    private String bkTrnrtipp;
    private String bkTrcotipp;
    private String question;
    private String infringement;
    private String bkTrinti;
    private String bkTrinre;
    private String bkTrrees;
    private String protocolCampaignCode;

}
