package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.specialist.db.entity.Specialist;
import es.dgc.gesco.model.specialist.dto.criteria.SpecialistCriteria;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecialistRepository extends BaseRepository<Specialist, Long>, QueryByCriteria<Specialist, SpecialistCriteria> {

}