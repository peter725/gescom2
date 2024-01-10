package es.consumo.junta_arbitral.modules.general.repository;

import es.consumo.junta_arbitral.modules.general.model.entity.RoadTypeEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadTypeRepository extends JJAARepository<RoadTypeEntity, Long> {
}
