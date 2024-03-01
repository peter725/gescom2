package es.consumo.gescom.modules.ipr.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IprCriteria extends FilterCriteria {
    private Long id;
    private String name;
    private String code;
    private String protocolCode;
    private Long campaignId;
    private Long protocolId;
    private List<IprQuestionDTO> iprQuestionDTOList;
}
