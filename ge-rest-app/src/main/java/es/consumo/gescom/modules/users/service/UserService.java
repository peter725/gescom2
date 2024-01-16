package es.consumo.gescom.modules.users.service;

import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;
import es.consumo.gescom.modules.users.model.dto.UserDTO;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface UserService extends CrudService<UserEntity, Long> {

    Page<UserEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserCriteria> wrapper);

    UserDTO findByUserId(Long id);

    UserEntity create(UserDTO roleDTO);

    UserEntity update(UserDTO userDTO);
}
