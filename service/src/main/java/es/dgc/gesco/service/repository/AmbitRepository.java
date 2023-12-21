package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.ambit.dto.criteria.AmbitCriteria;
import org.springframework.stereotype.Repository;


@Repository
public interface AmbitRepository extends BaseRepository<Ambit, Long>, QueryByCriteria<Ambit, AmbitCriteria> {

}