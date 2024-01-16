package es.consumo.gescom.modules.ambit.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.dto.AmbitDTO;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.ambit.service.AmbitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/ambit")
@Tag(name = "Ambit controller")
public class AmbitController extends AbstractCrudController<AmbitEntity, AmbitDTO, Long, FilterCriteria> {

    @Autowired
    public AmbitController(AmbitService service, DataConverter<AmbitEntity, AmbitDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<AmbitEntity.SimpleProjection>> findListByCriteria(AmbitCriteria ambitCriteria, @PathVariable  Long id) {
        Page<AmbitEntity.SimpleProjection> result =
                ((AmbitService) service).findAllAmbitById(new CriteriaWrapper<>(ambitCriteria), id);
        return ResponseEntity.ok(result);
    }
}
