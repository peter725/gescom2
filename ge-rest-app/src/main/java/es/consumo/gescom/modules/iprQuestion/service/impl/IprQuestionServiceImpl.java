package es.consumo.gescom.modules.iprQuestion.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.iprQuestion.model.criteria.IprQuestionCriteria;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.repository.IprQuestionRepository;
import es.consumo.gescom.modules.iprQuestion.service.IprQuestionService;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<IprQuestionEntity> findAllQuestionsByIprId(Long id) {
        return iprQuestionRepository.findAllQuestionsByIprId(id);
    }

    @Override
    public List<IprQuestionEntity> findAllQuestionsByIprCode(String code) {
        return iprQuestionRepository.findAllQuestionsByIprCode(code);
    }



}
