package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.model.modules.specialist.dto.criteria.SpecialistCriteria;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecialistRepository extends BaseRepository<Specialist, Long>, QueryByCriteria<Specialist, SpecialistCriteria> {

}