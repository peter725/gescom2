package es.consumo.junta_arbitral.modules.arbitration.repository;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.RepresentedEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentedRepository extends JJAARepository<RepresentedEntity, Long> {
}
