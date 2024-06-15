package es.consumo.gescom.modules.infringement.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.infringement.model.criteria.InfringementCriteria;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
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
            + "WHERE ("
            + ":#{#criteria.search} is null "
            + "OR UPPER(a.code) like :#{#criteria.search} "
            + "OR UPPER(a.infringement) like :#{#criteria.search}) "
    )
    Page<InfringementEntity.SimpleProjection> findAllByCriteria(InfringementCriteria criteria, Pageable pageable);
}