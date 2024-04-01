package es.consumo.gescom.modules.users.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/users")
@Tag(name = "User Controller")
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

    @PostMapping("/{id}/switch")
    public ResponseEntity<UserEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        UserEntity result = ((UserService) service).switchStatus(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Object> create(@Valid  @RequestBody UserDTO payload) {
        UserEntity result = ((UserService) service).create(payload);
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<Object> update(Long id, @Valid  @RequestBody UserDTO payload) {
        UserEntity result = ((UserService) service).update(payload);
        return ResponseEntity.ok(result);
    }

   /* @GetMapping("/data/{id}")
    public ResponseEntity<UserDTO> findUserDTO(@PathVariable Long id) {
        UserDTO result = ((UserService) service).findByUserId(id);
        return ResponseEntity.ok(result);
    }*/

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
}
