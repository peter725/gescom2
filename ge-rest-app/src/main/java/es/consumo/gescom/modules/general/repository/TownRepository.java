package es.consumo.gescom.modules.general.repository;

import es.consumo.gescom.modules.general.model.criteria.TownCriteria;
import es.consumo.gescom.modules.general.model.entity.TownEntity;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends GESCOMRepository<TownEntity, Long>, QueryByCriteria<TownEntity, TownCriteria> {

    @Override
    @Query(value = "SELECT t FROM TownEntity t "
            + "WHERE "
            + "(:#{#criteria.name} is null OR t.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.provinceId} is null OR t.province.id = :#{#criteria.provinceId}) "
    )
    Page<TownEntity> findAllByCriteria(TownCriteria criteria, Pageable pageable);
}
