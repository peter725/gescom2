package es.consumo.gescom.modules.proponent.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import es.consumo.gescom.modules.proponent.service.ProponentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/proponent")
@Tag(name = "Proponent controller")
public class ProponentController extends AbstractCrudController<ProponentEntity, ProponentDTO, Long, FilterCriteria> {

    @Autowired
    public ProponentController(ProponentService service, DataConverter<ProponentEntity, ProponentDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<ProponentEntity.SimpleProjection>> findListByCriteria(ProponentCriteria proponentCriteria, @PathVariable  Long id) {
        Page<ProponentEntity.SimpleProjection> result =
                ((ProponentService) service).findAllProponentById(new CriteriaWrapper<>(proponentCriteria), id);
        return ResponseEntity.ok(result);
    }
}
