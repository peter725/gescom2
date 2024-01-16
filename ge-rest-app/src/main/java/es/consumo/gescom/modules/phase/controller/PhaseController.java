package es.consumo.gescom.modules.phase.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.phase.model.criteria.PhaseCriteria;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import es.consumo.gescom.modules.phase.service.PhaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/phase_campaign")
@Tag(name = "Phase controller")
public class PhaseController extends AbstractCrudController<PhaseEntity, PhaseDTO, Long, FilterCriteria> {

    @Autowired
    public PhaseController(PhaseService service, DataConverter<PhaseEntity, PhaseDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<PhaseEntity.SimpleProjection>> findListByCriteria(PhaseCriteria phaseCriteria, @PathVariable  Long id) {
        Page<PhaseEntity.SimpleProjection> result =
                ((PhaseService) service).findAllPhaseById(new CriteriaWrapper<>(phaseCriteria), id);
        return ResponseEntity.ok(result);
    }
}
