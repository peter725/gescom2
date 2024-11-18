package es.consumo.gescom.modules.protocol.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtocolCriteria extends FilterCriteria {
    private Long id;
    private String code;
    private String name;
    private Long campaignId;
}
