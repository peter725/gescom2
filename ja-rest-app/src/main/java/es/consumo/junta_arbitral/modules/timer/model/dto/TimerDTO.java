package es.consumo.junta_arbitral.modules.timer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerDTO {
    private Integer maxDays;
    private Integer minDays;
    private String procedureName;
}
