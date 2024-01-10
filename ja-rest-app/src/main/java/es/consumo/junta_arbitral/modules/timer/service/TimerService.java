package es.consumo.junta_arbitral.modules.timer.service;

import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.timer.model.dto.CalculateDTO;
import es.consumo.junta_arbitral.modules.timer.model.entity.TimerEntity;

public interface TimerService extends CrudService<TimerEntity, Long>  {

    Integer calculate(CalculateDTO calculateDTO);
    
}
