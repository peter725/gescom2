package es.consumo.gescom.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * FilterCriteria implementation containing generic fields usually found in most enitties.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralCriteria extends FilterCriteria {

    // Common filter parameters that may be used on all entities

    private String name;

    private String description;

}
