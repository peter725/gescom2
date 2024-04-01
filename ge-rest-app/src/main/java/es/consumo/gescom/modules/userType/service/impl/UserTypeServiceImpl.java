package es.consumo.gescom.modules.userType.service.impl;


import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.userType.model.criteria.UserTypeCriteria;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import es.consumo.gescom.modules.userType.repository.UserTypeRepository;
import es.consumo.gescom.modules.userType.service.UserTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



@Service
public class UserTypeServiceImpl extends EntityCrudService<UserTypeEntity, Long> implements UserTypeService {

    protected UserTypeServiceImpl(GESCOMRepository<UserTypeEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<UserTypeEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserTypeCriteria> wrapper) {
        return ((UserTypeRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }
    
}