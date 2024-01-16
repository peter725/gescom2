package es.consumo.gescom.modules.module.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.module.model.criteria.ModuleCriteria;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;

@Repository
public interface ModuleRepository extends GESCOMRepository<ModuleEntity, Long>,
        QueryByCriteria<ModuleEntity.SimpleProjection, ModuleCriteria> {

    @Query(value = "SELECT a FROM ModuleEntity a "
        + "WHERE "
        + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
        + "AND (:#{#criteria.code} is null OR a.code LIKE :#{#criteria.code}) "
        + "AND (:#{#criteria.search} is null OR a.name LIKE :#{#criteria.search} OR a.code LIKE :#{#criteria.search}) "
    )
    Page<ModuleEntity.SimpleProjection> findAllByCriteria(ModuleCriteria criteria, Pageable pageable);

}
