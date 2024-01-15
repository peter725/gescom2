package es.consumo.gescom.modules.history.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.history.model.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JJAARepository<HistoryEntity, Long>  {

    @Query(value = "SELECT h FROM HistoryEntity h "
        + "WHERE "
        + "h.arbitration = :arbitration ")
    Page<HistoryEntity.SimpleProjection> findByArbitrationId(
    Pageable pageable,
    @Param("arbitration") ArbitrationEntity arbitration);
    
}
