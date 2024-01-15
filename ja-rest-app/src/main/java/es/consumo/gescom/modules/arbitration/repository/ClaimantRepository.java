package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.modules.arbitration.model.entity.ClaimantEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimantRepository extends JJAARepository<ClaimantEntity, Long> {
}
