package es.consumo.gescom.modules.arbitration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionFormDTO {

    private Long id;
    private String causes;
    private Long admissionStatus;
    
}
