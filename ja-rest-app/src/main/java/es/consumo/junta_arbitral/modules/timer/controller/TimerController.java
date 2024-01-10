package es.consumo.junta_arbitral.modules.timer.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.ReassignDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.service.ArbitrationService;
import es.consumo.junta_arbitral.modules.timer.model.dto.CalculateDTO;
import es.consumo.junta_arbitral.modules.timer.model.dto.TimerDTO;
import es.consumo.junta_arbitral.modules.timer.model.entity.TimerEntity;
import es.consumo.junta_arbitral.modules.timer.service.TimerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
