package es.consumo.gescom.modules.profile.service;


import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.profile.model.criteria.ProfileCriteria;
import es.consumo.gescom.modules.profile.model.dto.ProfileDTO;
import es.consumo.gescom.modules.profile.model.entity.ProfileEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfileService extends CrudService<ProfileEntity, Long> {

    Page<ProfileEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ProfileCriteria> wrapper);

    ProfileDTO createProfile(ProfileDTO payload);

}
