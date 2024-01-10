package es.consumo.junta_arbitral.modules.arbiter.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.modules.arbiter.model.criteria.ArbiterCriteria;
import es.consumo.junta_arbitral.modules.arbiter.model.criteria.CollegiateTypeCriteria;
import es.consumo.junta_arbitral.modules.arbiter.model.dto.ArbiterDTO;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.CollegiateTypeEntity;
import es.consumo.junta_arbitral.modules.arbiter.service.ArbiterService;
import es.consumo.junta_arbitral.modules.arbiter.service.CollegiateTypeService;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.dto.ArbitrationBoardDTO;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/arbiter")
@Tag(name = "Arbiter controller")
public class ArbiterController extends AbstractCrudController<ArbiterEntity, ArbiterDTO, Long, ArbiterCriteria> {

    private ModelMapper modelMapper;

    @Autowired
    CollegiateTypeService collegiateTypeService;

    @Autowired
    ArbiterService arbiterService;

    @Autowired
    public ArbiterController(ArbiterService service,
                             DataConverter<ArbiterEntity, ArbiterDTO> dataConverter,
                             ModelMapper modelMapper) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ArbiterEntity.SimpleProjection>> findListByCriteria(ArbiterCriteria arbiterCriteria) {
        convertDates(arbiterCriteria);
        Page<ArbiterEntity.SimpleProjection> result =
                ((ArbiterService) service).findAllSimpleEntity(new CriteriaWrapper<>(arbiterCriteria));
        //return ResponseEntity.ok(getPage(modelMapper, result, SimpleArbitrationDTO.class));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/validateExistName")
    public ResponseEntity<Boolean> validateExistsName(@RequestParam Long id,@RequestParam("name") String name) {
        return ResponseEntity.ok(arbiterService.existsByName(id,name));
    }

    @GetMapping("/collegiatelist")
    public ResponseEntity<Page<CollegiateTypeEntity.SimpleProjection>> findListCollegiateType(CollegiateTypeCriteria collegiateTypeCriteria) {
        Page<CollegiateTypeEntity.SimpleProjection> result =
                collegiateTypeService.findAllSimpleEntity(new CriteriaWrapper<>(collegiateTypeCriteria));
        //return ResponseEntity.ok(getPage(modelMapper, result, SimpleArbitrationDTO.class));
        return ResponseEntity.ok(result);
    }


    private void convertDates(ArbiterCriteria arbitrationBoardCriteria) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if (arbitrationBoardCriteria.getCreatedAtLTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(arbitrationBoardCriteria.getCreatedAtLTE(), formatter);
            arbitrationBoardCriteria.setCreatedAtLTEConvert(dateTime);
        }
        if (arbitrationBoardCriteria.getCreatedAtGTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(arbitrationBoardCriteria.getCreatedAtGTE(), formatter);
            arbitrationBoardCriteria.setCreatedAtGTEConvert(dateTime);
        }
        if (arbitrationBoardCriteria.getUpdatedAtLTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(arbitrationBoardCriteria.getUpdatedAtLTE(), formatter);
            arbitrationBoardCriteria.setUpdatedAtLTEConvert(dateTime);
        }
        if (arbitrationBoardCriteria.getUpdatedAtGTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(arbitrationBoardCriteria.getUpdatedAtGTE(), formatter);
            arbitrationBoardCriteria.setUpdatedAtGTEConvert(dateTime);
        }
    }
}
