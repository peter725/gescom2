package es.consumo.gescom.modules.iprQuestion.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.iprQuestion.model.criteria.IprQuestionCriteria;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.repository.IprQuestionRepository;
import es.consumo.gescom.modules.iprQuestion.service.IprQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class IprQuestionServiceImpl extends EntityCrudService<IprQuestionEntity, Long> implements IprQuestionService {
    protected IprQuestionServiceImpl(GESCOMRepository<IprQuestionEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private IprQuestionRepository iprQuestionRepository;

    @Override
    public Page<IprQuestionEntity.SimpleProjection> findAllIprQuestionByIdProtocol(CriteriaWrapper<IprQuestionCriteria> wrapper, Long id) {
        ///
        return ((IprQuestionRepository) repository).findAllIprQuestionByIdProtocol(wrapper.getCriteria().toPageable(), id);
    }
}
