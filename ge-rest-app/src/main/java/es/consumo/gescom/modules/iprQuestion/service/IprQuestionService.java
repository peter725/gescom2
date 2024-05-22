package es.consumo.gescom.modules.iprQuestion.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.iprQuestion.model.criteria.IprQuestionCriteria;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IprQuestionService extends CrudService<IprQuestionEntity, Long>{

    Page<IprQuestionEntity.SimpleProjection> findAllIprQuestionByIdProtocol(CriteriaWrapper<IprQuestionCriteria> wrapper, Long id);

    List<IprQuestionEntity> findAllQuestionsByIprId(Long id);

    List<IprQuestionEntity> findAllQuestionsByIprCode(String code);
}
