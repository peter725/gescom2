package es.consumo.gescom.modules.sumProtocol.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.sumProtocol.model.criteria.SumProtocolCriteria;
import es.consumo.gescom.modules.sumProtocol.model.dto.SumProtocolDTO;
import es.consumo.gescom.modules.sumProtocol.model.entity.SumProtocolEntity;
import es.consumo.gescom.modules.sumProtocol.service.SumProtocolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/sumProtocol")
@Tag(name = "SumProtocol controller")
public class SumProtocolController extends AbstractCrudController<SumProtocolEntity, SumProtocolDTO, Long, FilterCriteria> {

    @Autowired
    public SumProtocolController(SumProtocolService service, DataConverter<SumProtocolEntity, SumProtocolDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<SumProtocolEntity.SimpleProjection>> findListByCriteria(SumProtocolCriteria ambitCriteria, @PathVariable  Long id) {
        Page<SumProtocolEntity.SimpleProjection> result =
                ((SumProtocolService) service).findAllSumProtocolById(new CriteriaWrapper<>(ambitCriteria), id);
        return ResponseEntity.ok(result);
    }
}
