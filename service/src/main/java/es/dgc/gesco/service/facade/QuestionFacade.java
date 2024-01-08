package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.questions.converter.QuestionConverter;
import es.dgc.gesco.model.modules.questions.db.entity.Questions;
import es.dgc.gesco.model.modules.questions.dto.QuestionDTO;
import es.dgc.gesco.service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class QuestionFacade {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionConverter questionConverter;

    public Page<QuestionDTO> getQuestions(String protocol, String code, Pageable pageable) {
        Page<Questions> questionsPage = questionService.getQuestions(protocol, code,pageable);

        Page<QuestionDTO> questionDTOPage =  questionsPage.map(protocol1 -> questionConverter.convertQuestionsToDto(protocol1));

        return  questionDTOPage;
    }
}