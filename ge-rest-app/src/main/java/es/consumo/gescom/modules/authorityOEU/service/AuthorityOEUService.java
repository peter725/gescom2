package es.consumo.gescom.modules.authorityOEU.service;

import es.consumo.gescom.modules.authorityOEU.model.criteria.AuthorityOEUCriteria;
import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface AuthorityOEUService extends CrudService<AuthorityOEUEntity, Long>{

    Page<AuthorityOEUEntity.SimpleProjection> findAllAuthorityOEUByName(CriteriaWrapper<AuthorityOEUCriteria> wrapper, String name);
    
}
