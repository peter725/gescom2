package es.consumo.gescom.modules.arbiter.service.impl;


import es.consumo.gescom.modules.arbiter.model.criteria.ArbiterCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.gescom.modules.arbiter.repository.ArbiterRepository;
import es.consumo.gescom.modules.arbiter.service.ArbiterService;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class ArbiterServiceImpl extends EntityCrudService<ArbiterEntity, Long> implements ArbiterService {

    @Autowired
    ArbiterRepository arbitrationBoardRepository;

    @Autowired
    public ArbiterServiceImpl(JJAARepository<ArbiterEntity, Long> repository) {
        super(repository);
    }


    @Override
    public Page<ArbiterEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbiterCriteria> wrapper) {
        return ((ArbiterRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

    @Override
    public boolean existsByName(Long id, String name) {
        return arbitrationBoardRepository.existsCollegiateTypeEntityByName(id, name);
    }
}
