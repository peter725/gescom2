package es.consumo.gescom.modules.specialist.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.specialist.model.criteria.SpecialistCriteria;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import es.consumo.gescom.modules.specialist.service.SpecialistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/specialist")
@Tag(name = "Specialist controller")
public class SpecialistController extends AbstractCrudController<SpecialistEntity, SpecialistDTO, Long, FilterCriteria> {

    @Autowired
    public SpecialistController(SpecialistService service, DataConverter<SpecialistEntity, SpecialistDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<SpecialistEntity.SimpleProjection>> findListByCriteria(SpecialistCriteria specialistCriteria, @PathVariable  Long id) {
        Page<SpecialistEntity.SimpleProjection> result =
                ((SpecialistService) service).findAllSpecialistById(new CriteriaWrapper<>(specialistCriteria), id);
        return ResponseEntity.ok(result);
    }
}
