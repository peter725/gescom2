package es.consumo.junta_arbitral.modules.timer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateDTO {
    private Long timerId;
    private Long arbitrationId;
    private String process;
}
