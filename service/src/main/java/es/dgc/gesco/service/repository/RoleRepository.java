package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long>{

    @Override
    Page<Role> findAll(@Param("pageable") Pageable pageable);
}
