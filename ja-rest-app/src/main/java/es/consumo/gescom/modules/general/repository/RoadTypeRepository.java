package es.consumo.gescom.modules.general.repository;

import es.consumo.gescom.modules.general.model.entity.RoadTypeEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadTypeRepository extends JJAARepository<RoadTypeEntity, Long> {
}
