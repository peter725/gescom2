package es.consumo.gescom.modules.questions.repository;

import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface QuestionsRepository extends GESCOMRepository<QuestionsEntity, Long> {

    @Query(value = "SELECT h FROM QuestionsEntity h where h.id = :id ")
    Page<QuestionsEntity.SimpleProjection> findAllQuestionsById(Pageable pageable, @Param("id") Long id);

}
