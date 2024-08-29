package es.consumo.gescom.modules.users.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import es.consumo.gescom.commons.exception.AppException;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;
import es.consumo.gescom.modules.users.model.dto.UserDTO;
import es.consumo.gescom.modules.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/users")
public class UserController extends AbstractCrudController<UserEntity, UserDTO, Long, UserCriteria>{

    protected UserController(CrudService<UserEntity, Long> service, DataConverter<UserEntity, UserDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserEntity.SimpleProjection>> findListByCriteria(UserCriteria userCriteria) {
        convertDates(userCriteria);
        Page<UserEntity.SimpleProjection> result =
                ((UserService) service).findAllSimpleEntity(new CriteriaWrapper<>(userCriteria));
        return ResponseEntity.ok(result);
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
    public Optional<?> performFindById(Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return super.performFindById(id);
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
    public Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_CCAA", "ROLE_CICC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
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
    public ResponseEntity<UserEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                UserEntity result = ((UserService) service).switchStatus(changeStatus, id);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @Override
    public ResponseEntity<Object> create(@Valid  @RequestBody UserDTO payload) {
        UserEntity result = null;
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                result = ((UserService) service).create(payload);
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
    public ResponseEntity<Object> update(Long id, @Valid  @RequestBody UserDTO payload) {
        UserEntity result = null;
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                result = ((UserService) service).update(payload);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Error de la aplicacion, consulte con el administrador", e);
        }

    }

    private void convertDates(UserCriteria userCriteria) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if (userCriteria.getCreatedAtLTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(userCriteria.getCreatedAtLTE(), formatter);
            userCriteria.setCreatedAtLTEConvert(dateTime);
        }
        if (userCriteria.getCreatedAtGTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(userCriteria.getCreatedAtGTE(), formatter);
            userCriteria.setCreatedAtGTEConvert(dateTime);
        }
        if (userCriteria.getUpdatedAtLTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(userCriteria.getUpdatedAtLTE(), formatter);
            userCriteria.setUpdatedAtLTEConvert(dateTime);
        }
        if (userCriteria.getUpdatedAtGTE() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(userCriteria.getUpdatedAtGTE(), formatter);
            userCriteria.setUpdatedAtGTEConvert(dateTime);
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
