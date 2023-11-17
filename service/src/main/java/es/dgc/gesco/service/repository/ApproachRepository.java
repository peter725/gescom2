package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproachRepository
        extends BaseRepository<Approach, Long>,
        QueryByCriteria<Approach, UserCriteria> {

    @Override
    List<Approach> findAll();


}