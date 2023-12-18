package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.legislation.db.entity.Legislation;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislationRepository extends BaseRepository<Legislation, Long>{
}
