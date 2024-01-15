package es.consumo.gescom.modules.role.service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.role.model.dto.RoleDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;

public interface RoleService extends CrudService<RoleEntity, Long> {

    RoleEntity create(RoleDTO roleDTO);

    RoleEntity update(RoleDTO roleDTO);
}
