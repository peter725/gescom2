package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.modules.arbitration.model.constants.RequestStatus;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArbitrationStatusRepository extends JJAARepository<ArbitrationStatusEntity, Long> {

    Optional<ArbitrationStatusEntity> findByCode(RequestStatus requestStatus);

    Optional<ArbitrationStatusEntity> findById(Long id);
}
