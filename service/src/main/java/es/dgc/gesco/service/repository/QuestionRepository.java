package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.questions.db.entity.Questions;
import es.dgc.gesco.model.modules.questions.dto.criteria.QuestionsCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Questions, Long>, QueryByCriteria<Questions, QuestionsCriteria> {

    @Query(value = "SELECT qt FROM Questions qt WHERE qt.protocol LIKE %:protocol% AND qt.code LIKE %:code% ")
    Page<Questions> getQuestions(String protocol, String code, Pageable pageable);

}
