package es.consumo.gescom.modules.infringement.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.infringement.model.criteria.InfringementCriteria;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import es.consumo.gescom.modules.infringement.repository.InfringementRepository;
import es.consumo.gescom.modules.infringement.service.InfringementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class InfringementServiceImpl extends EntityCrudService<InfringementEntity, Long> implements InfringementService {
    protected InfringementServiceImpl(GESCOMRepository<InfringementEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private InfringementRepository campaignTypeRepository;

    public Page<InfringementEntity.SimpleProjection> findAllInfringementEntityById(CriteriaWrapper<InfringementCriteria> wrapper, Long id) {
        return ((InfringementRepository) repository).findAllInfringementEntityById(wrapper.getCriteria().toPageable(), id);
    }
    
    @Override
    protected Page<InfringementEntity.SimpleProjection> findAllFromCriteria(FilterCriteria criteria) {
    	InfringementCriteria infringimentCriteria = (InfringementCriteria) criteria;
        if (infringimentCriteria.getSearch() != null) {
        	infringimentCriteria.setSearch(infringimentCriteria.getSearch().toUpperCase());
        }
        infringimentCriteria.setSort(infringimentCriteria.getSort());
        
        return ((InfringementRepository) repository).findAllByCriteria(infringimentCriteria, criteria.toPageable());
    }

    @Override
    public Page<InfringementEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<InfringementCriteria> wrapper) {
        return ((InfringementRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }
}

