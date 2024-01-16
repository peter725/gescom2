package es.consumo.gescom.modules.arbitration.model.criteria;

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
public class ArbitrationCriteria extends FilterCriteria {

    private String status;
    private String arbitrationBoard;
    private String claimedSocialReason;
    private String claimedNif;
    private String tipoNoTif;
}
