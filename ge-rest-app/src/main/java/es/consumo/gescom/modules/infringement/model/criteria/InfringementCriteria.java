package es.consumo.gescom.modules.infringement.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfringementCriteria extends FilterCriteria {

    private Long id;
    private String code;
    private String infringement;
    private String description;
    private String bkTtitede;
    private String bkTticose;
    private String bkTtinoco2;
    private String bkTtiinsel1;
    private String bkTtinombree;
    private String bkTtinombrei;
    private String bkTtinombref;
    private String bkTtinocoe;
    private String bkTtinocoi;
    private String bkTtinocof;
    private String bkTtitedee;
    private String bkTtitedei;
    private String bkTtitedef;
    private String bkTticol4;
    private String bkTtiteno;
    private String bkTtitear;
    private String bkTtiinsel2;
    private String bkTticoici;

}
