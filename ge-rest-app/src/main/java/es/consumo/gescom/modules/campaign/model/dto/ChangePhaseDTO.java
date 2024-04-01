package es.consumo.gescom.modules.campaign.model.dto;

import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePhaseDTO implements Serializable {

    private Long id;
    private PhaseDTO phaseDTO;

}