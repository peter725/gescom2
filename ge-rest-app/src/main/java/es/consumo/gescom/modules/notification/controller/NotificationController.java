package es.consumo.gescom.modules.notification.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.role.model.criteria.RoleCriteria;
import es.consumo.gescom.modules.role.model.dto.RoleDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;

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
