package es.consumo.gescom.modules.totalProtocolResults.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalProtocolResultsRepository extends GESCOMRepository<TotalProtocolResultsEntity, Long> {

    @Query(value = "SELECT h FROM ProtocolResultsEntity h where h.id = :id ")
    Page<TotalProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(Pageable pageable, @Param("id") Long id);
}
