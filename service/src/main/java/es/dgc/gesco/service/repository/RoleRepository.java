package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long>{
}
