package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.proponent.dto.criteria.ProponentCriteria;
import org.springframework.stereotype.Repository;


@Repository
public interface ProponentRepository extends BaseRepository<Proponent, Long>, QueryByCriteria<Proponent, ProponentCriteria> {

}