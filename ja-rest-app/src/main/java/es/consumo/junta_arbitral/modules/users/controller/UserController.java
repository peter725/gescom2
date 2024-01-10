package es.consumo.junta_arbitral.modules.users.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.users.model.entity.UserEntity;
import es.consumo.junta_arbitral.modules.users.model.criteria.UserCriteria;
import es.consumo.junta_arbitral.modules.users.model.dto.UserDTO;
import es.consumo.junta_arbitral.modules.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping(ApiEndpoints.V1_API + "/user")
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
