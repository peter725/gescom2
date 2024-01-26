package es.consumo.gescom.modules.userType.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;

import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;


import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.userType.model.criteria.UserTypeCriteria;
import es.consumo.gescom.modules.userType.model.dto.UserTypeDTO;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import es.consumo.gescom.modules.userType.service.UserTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/userType")
@Tag(name = "UserType controller")
public class UserTypeController extends AbstractCrudController<UserTypeEntity, UserTypeDTO, Long, UserTypeCriteria> {

    protected UserTypeController(CrudService<UserTypeEntity, Long> service,
                                 DataConverter<UserTypeEntity, UserTypeDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserTypeEntity.SimpleProjection>> findListByCriteria(UserTypeCriteria profileCriteria) {

        Page<UserTypeEntity.SimpleProjection> result =
                ((UserTypeService) service).findAllSimpleEntity(new CriteriaWrapper<>(profileCriteria));
        return ResponseEntity.ok(result);
    }


}
