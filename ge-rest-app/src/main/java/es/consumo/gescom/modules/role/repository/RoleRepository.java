package es.consumo.gescom.modules.role.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.role.model.criteria.RoleCriteria;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GESCOMRepository<RoleEntity, Long>,
        QueryByCriteria<RoleEntity, RoleCriteria> {

    @Query(value = "SELECT a FROM RoleEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR UPPER(a.name) LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.search} is null OR UPPER(a.name) LIKE :#{#criteria.search}) "
    )
    Page<RoleEntity> findAllByCriteria(RoleCriteria criteria, Pageable pageable);

}
