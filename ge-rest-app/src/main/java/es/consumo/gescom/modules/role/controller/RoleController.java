package es.consumo.gescom.modules.role.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.role.model.criteria.RoleCriteria;
import es.consumo.gescom.modules.role.model.dto.RoleNewDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/roles")
@Tag(name = "Role controller")
@PreAuthorize("hasRole('ROLE_ADMINISTRADOR DE ÁMBITO')")
public class RoleController extends AbstractCrudController<RoleEntity, RoleNewDTO, Long, RoleCriteria> {

    protected RoleController(CrudService<RoleEntity, Long> service,
                             DataConverter<RoleEntity, RoleNewDTO> dataConverter) {
        super(service, dataConverter);
    }


    @PostMapping("/{id}/switch")
    public ResponseEntity<RoleEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
    	RoleEntity result = ((RoleService) service).switchStatus(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    /*@Override
    public ResponseEntity<Object> findAll(@Valid RoleCriteria criteria) {
        return super.findAll(criteria);
    }*/

    @Override
    protected Optional<?> performFindById(Long id) {
        return Optional.of(
                ((RoleService) service).findRoleById(id)
        );
    }


    /*@Override
    protected void performDelete(Long id) {
        super.performDelete(id);
    }*/

    @Override
    public ResponseEntity<Object> create(@Valid  @RequestBody RoleNewDTO payload) {
        RoleEntity result = ((RoleService) service).create(payload);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Object> update(Long id,@Valid  @RequestBody RoleNewDTO payload) {
        RoleEntity result = ((RoleService) service).update(payload);
        return ResponseEntity.ok(result);
    }

}
