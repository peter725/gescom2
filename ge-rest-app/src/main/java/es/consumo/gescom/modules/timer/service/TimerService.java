package es.consumo.gescom.modules.timer.service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.timer.model.dto.CalculateDTO;
import es.consumo.gescom.modules.timer.model.entity.TimerEntity;

public interface TimerService extends CrudService<TimerEntity, Long>  {

    Integer calculate(CalculateDTO calculateDTO);
    
}
