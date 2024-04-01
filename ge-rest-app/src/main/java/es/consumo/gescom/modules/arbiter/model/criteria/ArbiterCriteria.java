package es.consumo.gescom.modules.arbiter.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArbiterCriteria extends FilterCriteria {

    private String name;
    private String surnames;
    private String nif;
    private String createdAtLTE;
    private String createdAtGTE;
    private String updatedAtLTE;
    private String updatedAtGTE;

    private LocalDateTime createdAtLTEConvert;
    private LocalDateTime createdAtGTEConvert;
    private LocalDateTime updatedAtLTEConvert;
    private LocalDateTime updatedAtGTEConvert;
}
