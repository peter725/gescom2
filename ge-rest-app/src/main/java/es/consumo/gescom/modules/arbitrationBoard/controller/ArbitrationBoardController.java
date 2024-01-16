package es.consumo.gescom.modules.arbitrationBoard.controller;


import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.dto.ArbitrationBoardDTO;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.modules.arbitrationBoard.service.ArbitrationBoardService;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/arbitrationBoard")
@Tag(name = "Arbitration board controller")
public class ArbitrationBoardController extends AbstractCrudController<ArbitrationBoardEntity, ArbitrationBoardDTO, Long, ArbitrationBoardCriteria> {

    @Autowired
    private ArbitrationBoardService arbitrationBoardService;
    @Autowired
    public ArbitrationBoardController(ArbitrationBoardService service,
                                      DataConverter<ArbitrationBoardEntity, ArbitrationBoardDTO> dataConverter,
                                      ModelMapper modelMapper) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ArbitrationBoardEntity.SimpleProjection>> findListByCriteria(ArbitrationBoardCriteria arbitrationBoardCriteria) {

        Page<ArbitrationBoardEntity.SimpleProjection> result =
                ((ArbitrationBoardService) service).findAllSimpleEntity(new CriteriaWrapper<>(arbitrationBoardCriteria));
        //return ResponseEntity.ok(getPage(modelMapper, result, SimpleArbitrationDTO.class));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/validateExistName")
    public ResponseEntity<Boolean> validateExistsName(@RequestParam Long id,@RequestParam("name") String name) {
        return ResponseEntity.ok(arbitrationBoardService.existsByName(id,name));
    }


}
