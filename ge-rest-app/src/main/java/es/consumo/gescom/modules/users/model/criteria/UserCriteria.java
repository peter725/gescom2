package es.consumo.gescom.modules.users.model.criteria;

import java.time.LocalDateTime;

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
public class UserCriteria extends FilterCriteria {
    private String name;
    private String surname;
    private String dni;
    private String lastSurname;
    private String createdAtLTE;
    private String createdAtGTE;
    private String updatedAtLTE;
    private String updatedAtGTE;

    private LocalDateTime createdAtLTEConvert;
    private LocalDateTime createdAtGTEConvert;
    private LocalDateTime updatedAtLTEConvert;
    private LocalDateTime updatedAtGTEConvert;
}