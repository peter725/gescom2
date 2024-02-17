package es.consumo.gescom.modules.questions.service.impl;

import es.consumo.gescom.modules.questions.model.criteria.QuestionsCriteria;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import es.consumo.gescom.modules.questions.repository.QuestionsRepository;
import es.consumo.gescom.modules.questions.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.util.List;


@Service
public class QuestionsServiceImpl extends EntityCrudService<QuestionsEntity, Long> implements QuestionsService {
    protected QuestionsServiceImpl(GESCOMRepository<QuestionsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Page<QuestionsEntity.SimpleProjection> findAllQuestionsById(CriteriaWrapper<QuestionsCriteria> wrapper, Long id) {
        return ((QuestionsRepository) repository).findAllQuestionsById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public List<QuestionsEntity> findAllQuestionsByProtocolId(Long id) {
        return questionsRepository.findAllQuestionsByProtocolId(id);
    }

    @Override
    public List<QuestionsEntity> findAllQuestionsByProtocolCode(String code) {
        return questionsRepository.findAllQuestionsByProtocolCode(code);
    }
}
