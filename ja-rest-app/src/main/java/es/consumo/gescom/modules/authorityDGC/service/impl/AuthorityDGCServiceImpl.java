package es.consumo.gescom.modules.authorityDGC.service.impl;

import es.consumo.gescom.modules.authorityDGC.model.criteria.AuthorityDGCCriteria;
import es.consumo.gescom.modules.authorityDGC.model.entity.AuthorityDGCEntity;
import es.consumo.gescom.modules.authorityDGC.repository.AuthorityDGCRepository;
import es.consumo.gescom.modules.authorityDGC.service.AuthorityDGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class AuthorityDGCServiceImpl extends EntityCrudService<AuthorityDGCEntity, Long> implements AuthorityDGCService {
    protected AuthorityDGCServiceImpl(GESCOMRepository<AuthorityDGCEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AuthorityDGCRepository authorityDGCRepository;

    @Override
    public Page<AuthorityDGCEntity.SimpleProjection> findAllAuthorityDGCByName(CriteriaWrapper<AuthorityDGCCriteria> wrapper, String name) {
        return ((AuthorityDGCRepository) repository).findAllAuthorityDGCByName(wrapper.getCriteria().toPageable(), name);
    }
}
