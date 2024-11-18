package es.consumo.gescom.modules.iprQuestion.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IprQuestionRepository extends GESCOMRepository<IprQuestionEntity, Long>{

    @Query(value = "SELECT h FROM IprQuestionEntity h where h.id = :id ")
    Page<IprQuestionEntity.SimpleProjection> findAllIprQuestionByIdProtocol(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT h FROM IprQuestionEntity h where h.iprId.id = :id ")
    List<IprQuestionEntity> findAllQuestionsByIprId(Long id);

    @Query(value = "SELECT h FROM IprQuestionEntity h where h.iprCode = :code ")
    List<IprQuestionEntity> findAllQuestionsByIprCode(String code);


}
