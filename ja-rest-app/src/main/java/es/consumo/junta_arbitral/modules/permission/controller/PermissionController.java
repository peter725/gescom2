package es.consumo.junta_arbitral.modules.permission.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.junta_arbitral.modules.permission.model.dto.PermissionDTO;
import es.consumo.junta_arbitral.modules.permission.model.entity.PermissionEntity;
import es.consumo.junta_arbitral.modules.permission.service.PermissionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/permission")
@Tag(name = "Permission controller")
public class PermissionController extends AbstractCrudController<PermissionEntity, PermissionDTO, Long, PermissionCriteria> {

    protected PermissionController(CrudService<PermissionEntity, Long> service,
            DataConverter<PermissionEntity, PermissionDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PermissionEntity.SimpleProjection>> findListByCriteria(PermissionCriteria permissionCriteria) {

        Page<PermissionEntity.SimpleProjection> result =
                ((PermissionService) service).findAllSimpleEntity(new CriteriaWrapper<>(permissionCriteria));
        return ResponseEntity.ok(result);
    }


}
