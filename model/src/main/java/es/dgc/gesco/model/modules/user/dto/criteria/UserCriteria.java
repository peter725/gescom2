package es.dgc.gesco.model.modules.user.dto.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.dgc.gesco.model.commom.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class UserCriteria extends FilterCriteria {
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String nif;
    private String position;
    private Long rolId;
    private Long phoneId;
    private Long emaild;
    private Long nationalAutority;
}

