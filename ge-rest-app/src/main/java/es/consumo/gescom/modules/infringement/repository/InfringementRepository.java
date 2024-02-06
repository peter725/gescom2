package es.consumo.gescom.modules.infringement.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.infringement.model.criteria.InfringementCriteria;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InfringementRepository extends GESCOMRepository<InfringementEntity, Long> , QueryByCriteria<InfringementEntity.SimpleProjection, InfringementCriteria> {

    @Query(value = "SELECT h FROM InfringementEntity h where h.id = :id ")
    Page<InfringementEntity.SimpleProjection> findAllInfringementEntityById(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT a FROM InfringementEntity a "
            + "WHERE "
            + "(:#{#criteria.infringement} is null OR a.infringement LIKE :#{#criteria.infringement}) "
            + "AND (:#{#criteria.description} is null OR a.description LIKE :#{#criteria.description}) "
            + "AND (:#{#criteria.code} is null OR a.code LIKE :#{#criteria.code}) "
            + "AND (:#{#criteria.createdAt} is null OR a.createdAt <= :#{#criteria.createdAt}) "
            + "AND (:#{#criteria.createdBy} is null OR a.createdBy >= :#{#criteria.createdBy}) "
            + "AND (:#{#criteria.updatedAt} is null OR a.updatedAt <= :#{#criteria.updatedAt}) "
            + "AND (:#{#criteria.updatedBy} is null OR a.updatedBy >= :#{#criteria.updatedBy}) "
            + "AND (:#{#criteria.search} is null OR a.infringement like :#{#criteria.search} OR a.infringement like :#{#criteria.search} OR a.code like :#{#criteria.search} "
            + "OR a.description like :#{#criteria.search})"
    )
    Page<InfringementEntity.SimpleProjection> findAllByCriteria(InfringementCriteria criteria, Pageable pageable);
}