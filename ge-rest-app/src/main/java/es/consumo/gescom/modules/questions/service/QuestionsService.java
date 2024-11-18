package es.consumo.gescom.modules.questions.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.questions.model.criteria.QuestionsCriteria;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionsService extends CrudService<QuestionsEntity, Long>{

    Page<QuestionsEntity.SimpleProjection> findAllQuestionsById(CriteriaWrapper<QuestionsCriteria> wrapper, Long id);

    List<QuestionsEntity> findAllQuestionsByProtocolId(Long id);

    List<QuestionsEntity> findAllQuestionsByProtocolCode(String code);
    
}
