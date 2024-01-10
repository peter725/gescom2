package es.consumo.junta_arbitral.modules.role.service;

import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.role.model.dto.RoleDTO;
import es.consumo.junta_arbitral.modules.role.model.entity.RoleEntity;

public interface RoleService extends CrudService<RoleEntity, Long> {

    RoleEntity create(RoleDTO roleDTO);

    RoleEntity update(RoleDTO roleDTO);
}
