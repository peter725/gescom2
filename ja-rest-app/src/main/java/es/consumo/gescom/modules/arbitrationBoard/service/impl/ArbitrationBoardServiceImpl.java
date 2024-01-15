package es.consumo.gescom.modules.arbitrationBoard.service.impl;

import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.modules.arbitrationBoard.repository.ArbitrationBoardRepository;
import es.consumo.gescom.modules.arbitrationBoard.service.ArbitrationBoardService;
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
public class ArbitrationBoardServiceImpl extends EntityCrudService<ArbitrationBoardEntity, Long> implements ArbitrationBoardService {

    @Autowired
    private ArbitrationBoardRepository arbitrationBoardRepository;

    @Autowired
    public ArbitrationBoardServiceImpl(JJAARepository<ArbitrationBoardEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Page<ArbitrationBoardEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbitrationBoardCriteria> wrapper) {
        return ((ArbitrationBoardRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

    @Override
    public boolean existsByName(Long id, String name) {
        return arbitrationBoardRepository.existsArbitrationBoardEntityByName(id, name);
    }
}
