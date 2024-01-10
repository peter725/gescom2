package es.consumo.junta_arbitral.modules.history.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.history.model.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JJAARepository<HistoryEntity, Long>  {

    @Query(value = "SELECT h FROM HistoryEntity h "
        + "WHERE "
        + "h.arbitration = :arbitration ")
    Page<HistoryEntity.SimpleProjection> findByArbitrationId(
    Pageable pageable,
    @Param("arbitration") ArbitrationEntity arbitration);
    
}
