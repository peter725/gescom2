package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends BaseRepository<UserType, Long>,
        QueryByCriteria<UserType, UserCriteria> {


}
