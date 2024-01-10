package es.consumo.junta_arbitral.modules.notification.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.role.model.criteria.RoleCriteria;
import es.consumo.junta_arbitral.modules.role.model.dto.RoleDTO;
import es.consumo.junta_arbitral.modules.role.model.entity.RoleEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/notification")
@Tag(name = "Notification controller")
public class NotificationController extends AbstractCrudController<RoleEntity, RoleDTO, Long, RoleCriteria> {

    protected NotificationController(CrudService<RoleEntity, Long> service,
            DataConverter<RoleEntity, RoleDTO> dataConverter) {
        super(service, dataConverter);
    }
    
}
