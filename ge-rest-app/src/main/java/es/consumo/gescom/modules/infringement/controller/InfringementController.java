package es.consumo.gescom.modules.infringement.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.infringement.model.criteria.InfringementCriteria;
import es.consumo.gescom.modules.infringement.model.dto.InfringementDTO;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import es.consumo.gescom.modules.infringement.service.InfringementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiEndpoints.V1_API + "/infringement")
@Tag(name = "Infringement Controller")
public class InfringementController extends AbstractCrudController<InfringementEntity, InfringementDTO, Long, FilterCriteria> {

    @Autowired
    public InfringementController(InfringementService service, DataConverter<InfringementEntity, InfringementDTO> campaingTypeDto) {
        super(service, campaingTypeDto);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<InfringementEntity.SimpleProjection>> findListByCriteria(InfringementCriteria campaignTypeCriteria, @PathVariable Long id) {
        Page<InfringementEntity.SimpleProjection> result =
                ((InfringementService) service).findAllInfringementEntityById(new CriteriaWrapper<>(campaignTypeCriteria), id);
        return ResponseEntity.ok(result);
    }
}