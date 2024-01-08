package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.questions.db.entity.Questions;
import es.dgc.gesco.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionService {


    private final QuestionRepository questionRepository;

    public Page<Questions> getQuestions(String protocol, String code, Pageable pageable) {
        Page<Questions> questionsPage = questionRepository.getQuestions(protocol, code, pageable);
        return questionsPage;
    }
}
