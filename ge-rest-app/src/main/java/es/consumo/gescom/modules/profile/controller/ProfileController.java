package es.consumo.gescom.modules.profile.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;


import es.consumo.gescom.modules.profile.model.criteria.ProfileCriteria;
import es.consumo.gescom.modules.profile.model.dto.ProfileDTO;
import es.consumo.gescom.modules.profile.model.entity.ProfileEntity;
import es.consumo.gescom.modules.profile.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/profile")
@Tag(name = "Profile controller")
public class ProfileController extends AbstractCrudController<ProfileEntity, ProfileDTO, Long, ProfileCriteria> {

    protected ProfileController(CrudService<ProfileEntity, Long> service,
                                DataConverter<ProfileEntity, ProfileDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProfileEntity.SimpleProjection>> findListByCriteria(ProfileCriteria profileCriteria) {

        Page<ProfileEntity.SimpleProjection> result =
                ((ProfileService) service).findAllSimpleEntity(new CriteriaWrapper<>(profileCriteria));
        return ResponseEntity.ok(result);
    }

    @Override
    protected ProfileDTO performCreate(ProfileDTO payload) {
        return ((ProfileService) service).createProfile(payload);
    }


}
