package es.consumo.junta_arbitral.modules.arbitration.repository;

import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface ArbitrationStatusRepository extends JJAARepository<ArbitrationStatusEntity, Long> {

    Optional<ArbitrationStatusEntity> findByCode(RequestStatus requestStatus);

    Optional<ArbitrationStatusEntity> findById(Long id);
}
