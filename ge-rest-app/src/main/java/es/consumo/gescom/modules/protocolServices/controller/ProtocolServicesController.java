package es.consumo.gescom.modules.protocolServices.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.protocolServices.model.criteria.ProtocolServicesCriteria;
import es.consumo.gescom.modules.protocolServices.model.dto.ProtocolServicesDTO;
import es.consumo.gescom.modules.protocolServices.model.entity.ProtocolServicesEntity;
import es.consumo.gescom.modules.protocolServices.service.ProtocolServicesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/protocolServices")
@Tag(name = "Protocol Services controller")
public class ProtocolServicesController extends AbstractCrudController<ProtocolServicesEntity, ProtocolServicesDTO, Long, FilterCriteria> {

    @Autowired
    public ProtocolServicesController(ProtocolServicesService service, DataConverter<ProtocolServicesEntity, ProtocolServicesDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<ProtocolServicesEntity.SimpleProjection>> findListByCriteria(ProtocolServicesCriteria servicesCriteria, @PathVariable  Long id) {
        Page<ProtocolServicesEntity.SimpleProjection> result =
                ((ProtocolServicesService) service).findAllProtocolServicesById(new CriteriaWrapper<>(servicesCriteria), id);
        return ResponseEntity.ok(result);
    }
}
