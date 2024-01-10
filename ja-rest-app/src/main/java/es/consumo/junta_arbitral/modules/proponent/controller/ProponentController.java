package es.consumo.junta_arbitral.modules.proponent.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.junta_arbitral.modules.ambit.model.dto.AmbitDTO;
import es.consumo.junta_arbitral.modules.ambit.model.entity.AmbitEntity;
import es.consumo.junta_arbitral.modules.ambit.service.AmbitService;
import es.consumo.junta_arbitral.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.junta_arbitral.modules.proponent.model.dto.ProponentDTO;
import es.consumo.junta_arbitral.modules.proponent.model.entity.ProponentEntity;
import es.consumo.junta_arbitral.modules.proponent.service.ProponentService;
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
