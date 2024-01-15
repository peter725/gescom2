package es.consumo.gescom.modules.permission.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JJAARepository<PermissionEntity, Long>,
        QueryByCriteria<PermissionEntity.SimpleProjection, PermissionCriteria> {

    @Query(value = "SELECT a FROM PermissionEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.code} is null OR a.code LIKE :#{#criteria.code}) "
            + "AND (:#{#criteria.search} is null OR a.name LIKE :#{#criteria.search} OR a.code LIKE :#{#criteria.search}) "
    )
    Page<PermissionEntity.SimpleProjection> findAllByCriteria(PermissionCriteria criteria, Pageable pageable);

}
