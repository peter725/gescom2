package es.consumo.gescom.modules.profile.service.impl;

import es.consumo.gescom.modules.profile.model.criteria.ProfileCriteria;
import es.consumo.gescom.modules.profile.model.entity.ProfileEntity;
import es.consumo.gescom.modules.profile.repository.ProfileRepository;
import es.consumo.gescom.modules.profile.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class ProfileServiceImpl extends EntityCrudService<ProfileEntity, Long> implements ProfileService {

    protected ProfileServiceImpl(GESCOMRepository<ProfileEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<ProfileEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ProfileCriteria> wrapper) {
        return ((ProfileRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }
    
}