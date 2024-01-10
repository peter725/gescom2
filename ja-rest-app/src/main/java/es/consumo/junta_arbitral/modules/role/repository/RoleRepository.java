package es.consumo.junta_arbitral.modules.role.repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.db.repository.QueryByCriteria;
import es.consumo.junta_arbitral.modules.role.model.criteria.RoleCriteria;
import es.consumo.junta_arbitral.modules.role.model.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JJAARepository<RoleEntity, Long>,
        QueryByCriteria<RoleEntity, RoleCriteria> {

    @Query(value = "SELECT a FROM RoleEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.search} is null OR a.name LIKE :#{#criteria.search}) "
    )
    Page<RoleEntity> findAllByCriteria(RoleCriteria criteria, Pageable pageable);

}
