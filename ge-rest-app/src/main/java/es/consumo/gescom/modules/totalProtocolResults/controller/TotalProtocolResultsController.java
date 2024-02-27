package es.consumo.gescom.modules.totalProtocolResults.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.totalProtocolResults.model.criteria.TotalProtocolResultsCriteria;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/total_protocol_results")
@Tag(name = "TotalProtocol controller")
public class TotalProtocolResultsController extends AbstractCrudController<TotalProtocolResultsEntity, TotalProtocolResultsDTO, Long, FilterCriteria> {

    @Autowired
    public TotalProtocolResultsController(TotalProtocolResultsService service, DataConverter<TotalProtocolResultsEntity, TotalProtocolResultsDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<TotalProtocolResultsEntity.SimpleProjection>> findListByCriteria(TotalProtocolResultsCriteria ambitCriteria, @PathVariable Long id) {
        Page<TotalProtocolResultsEntity.SimpleProjection> result =
                ((TotalProtocolResultsService) service).findAllSumProtocolById(new CriteriaWrapper<>(ambitCriteria), id);
        return ResponseEntity.ok(result);
    }
    
    
    
}
