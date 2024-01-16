package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.modules.arbitration.model.entity.ClaimantEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimantRepository extends GESCOMRepository<ClaimantEntity, Long> {
}
