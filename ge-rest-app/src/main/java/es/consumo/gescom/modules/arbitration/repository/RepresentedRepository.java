package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.modules.arbitration.model.entity.RepresentedEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentedRepository extends GESCOMRepository<RepresentedEntity, Long> {
}
