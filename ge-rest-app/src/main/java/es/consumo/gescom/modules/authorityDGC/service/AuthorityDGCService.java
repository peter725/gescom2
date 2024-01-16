package es.consumo.gescom.modules.authorityDGC.service;

import es.consumo.gescom.modules.authorityDGC.model.criteria.AuthorityDGCCriteria;
import es.consumo.gescom.modules.authorityDGC.model.entity.AuthorityDGCEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface AuthorityDGCService extends CrudService<AuthorityDGCEntity, Long>{

    Page<AuthorityDGCEntity.SimpleProjection> findAllAuthorityDGCByName(CriteriaWrapper<AuthorityDGCCriteria> wrapper, String name);
    
}
