package es.consumo.gescom.modules.timer.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.timer.model.dto.CalculateDTO;
import es.consumo.gescom.modules.timer.model.dto.TimerDTO;
import es.consumo.gescom.modules.timer.model.entity.TimerEntity;
import es.consumo.gescom.modules.timer.service.TimerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/timer")
@Tag(name = "Timer controller")
public class TimerController extends AbstractCrudController<TimerEntity, TimerDTO, Long, FilterCriteria> {

    protected TimerController(CrudService<TimerEntity, Long> service,
            DataConverter<TimerEntity, TimerDTO> dataConverter) {
        super(service, dataConverter);
    }

    @PostMapping("/calculate")
    public ResponseEntity<Integer> calculateTime(@RequestBody CalculateDTO calculateDTO) {
        Integer result = ((TimerService) service).calculate(calculateDTO);
        return ResponseEntity.ok(result);
    }

}
