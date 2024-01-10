package es.consumo.junta_arbitral.modules.arbitration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDTO {
    private Long arbitrationId;
    private AdmissionFormDTO admissionForm;
}
