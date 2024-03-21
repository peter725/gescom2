package es.consumo.gescom.modules.role.service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.role.model.dto.RoleNewDTO;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;

public interface RoleService extends CrudService<RoleEntity, Long> {

    RoleEntity create(RoleNewDTO roleDTO);

    RoleEntity update(RoleNewDTO roleDTO);
}
