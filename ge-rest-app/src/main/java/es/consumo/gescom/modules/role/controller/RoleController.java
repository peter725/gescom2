package es.consumo.gescom.modules.role.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.role.model.criteria.RoleCriteria;
import es.consumo.gescom.modules.role.model.dto.RoleNewDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.service.RoleService;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    // Variable de clase para almacenar los detalles del usuario y los roles
    private UserDetails userDetails;
    private LoginEntity loginEntity;

    // Método para inicializar la autenticación y verificar roles
    private void initializeAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }
        userDetails = (UserDetails) authentication.getPrincipal();
        loginEntity = (LoginEntity) userDetails;
        Hibernate.initialize(loginEntity.getRoles());
    }

    @Override
    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC","ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return super.performFindAll(criteriaWrapper);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PostMapping("/{id}/switch")
    public ResponseEntity<RoleEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
    	try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                RoleEntity result = ((RoleService) service).switchStatus(changeStatus, id);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }

    }

    @Override
    protected Optional<?> performFindById(Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return Optional.of(((RoleService) service).findRoleById(id));
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            return Optional.of(new ResponseEntity<>(e.getReason(), e.getStatus()));
        } catch (Exception e) {
            return Optional.of(new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

    @Override
    public ResponseEntity<Object> create(@Valid  @RequestBody RoleNewDTO payload) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                RoleEntity result = ((RoleService) service).create(payload);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }  catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @Override
    public ResponseEntity<Object> update(Long id,@Valid  @RequestBody RoleNewDTO payload) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                RoleEntity result = ((RoleService) service).update(payload);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }  catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    private boolean hasAnyRole(UserDetails userDetails, String... roles) {
        for (String role : roles) {
        if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
            return true;
        }
    }
        return false;
    }

}
