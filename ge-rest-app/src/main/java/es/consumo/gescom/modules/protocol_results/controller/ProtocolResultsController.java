package es.consumo.gescom.modules.protocol_results.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/protocol_results")
@Tag(name = "ProtocolResults controller")
public class ProtocolResultsController extends AbstractCrudController<ProtocolResultsEntity, ProtocolResultsDTO, Long, FilterCriteria> {

    @Autowired
    public ProtocolResultsController(ProtocolResultsService service, DataConverter<ProtocolResultsEntity, ProtocolResultsDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<ProtocolResultsEntity.SimpleProjection>> findListByCriteria(ProtocolResultsCriteria ambitCriteria, @PathVariable  Long id) {
        Page<ProtocolResultsEntity.SimpleProjection> result =
                ((ProtocolResultsService) service).findAllSumProtocolById(new CriteriaWrapper<>(ambitCriteria), id);
        return ResponseEntity.ok(result);
    }
}
