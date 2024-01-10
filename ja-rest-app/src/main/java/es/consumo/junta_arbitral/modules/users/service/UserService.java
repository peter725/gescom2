package es.consumo.junta_arbitral.modules.users.service;

import es.consumo.junta_arbitral.modules.users.model.entity.UserEntity;
import es.consumo.junta_arbitral.modules.users.model.criteria.UserCriteria;
import es.consumo.junta_arbitral.modules.users.model.dto.UserDTO;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface UserService extends CrudService<UserEntity, Long> {

    Page<UserEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserCriteria> wrapper);

    UserDTO findByUserId(Long id);

    UserEntity create(UserDTO roleDTO);

    UserEntity update(UserDTO userDTO);
}
