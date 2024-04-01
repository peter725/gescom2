package es.consumo.gescom.modules.authorityOEU.service.impl;

import es.consumo.gescom.modules.authorityOEU.model.criteria.AuthorityOEUCriteria;
import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import es.consumo.gescom.modules.authorityOEU.repository.AuthorityOEURepository;
import es.consumo.gescom.modules.authorityOEU.service.AuthorityOEUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class AuthorityOEUServiceImpl extends EntityCrudService<AuthorityOEUEntity, Long> implements AuthorityOEUService {
    protected AuthorityOEUServiceImpl(GESCOMRepository<AuthorityOEUEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AuthorityOEURepository authorityOEURepository;

    @Override
    public Page<AuthorityOEUEntity.SimpleProjection> findAllAuthorityOEUByName(CriteriaWrapper<AuthorityOEUCriteria> wrapper, String name) {
        return ((AuthorityOEURepository) repository).findAllAuthorityOEUByName(wrapper.getCriteria().toPageable(), name);
    }
}
