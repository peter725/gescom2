package es.consumo.gescom.modules.userType.service;


import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.userType.model.criteria.UserTypeCriteria;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import org.springframework.data.domain.Page;

public interface UserTypeService extends CrudService<UserTypeEntity, Long> {
    Page<UserTypeEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserTypeCriteria> wrapper);
    
}
