package es.consumo.junta_arbitral.modules.role.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.role.model.criteria.RoleCriteria;
import es.consumo.junta_arbitral.modules.role.model.dto.RoleDTO;
import es.consumo.junta_arbitral.modules.role.model.entity.RoleEntity;
import es.consumo.junta_arbitral.modules.role.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/role")
@Tag(name = "Role controller")
public class RoleController extends AbstractCrudController<RoleEntity, RoleDTO, Long, RoleCriteria> {

    protected RoleController(CrudService<RoleEntity, Long> service,
                             DataConverter<RoleEntity, RoleDTO> dataConverter) {
        super(service, dataConverter);
    }


    @Override
    public ResponseEntity<Object> create(@Valid  @RequestBody RoleDTO payload) {
        RoleEntity result = ((RoleService) service).create(payload);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Object> update(Long id,@Valid  @RequestBody RoleDTO payload) {
        RoleEntity result = ((RoleService) service).update(payload);
        return ResponseEntity.ok(result);
    }

}
