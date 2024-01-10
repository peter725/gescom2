package es.consumo.junta_arbitral.modules.general.repository;

import es.consumo.junta_arbitral.modules.general.model.criteria.TownCriteria;
import es.consumo.junta_arbitral.modules.general.model.entity.TownEntity;
import es.consumo.junta_arbitral.commons.db.repository.QueryByCriteria;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JJAARepository<TownEntity, Long>, QueryByCriteria<TownEntity, TownCriteria> {

    @Override
    @Query(value = "SELECT t FROM TownEntity t "
            + "WHERE "
            + "(:#{#criteria.name} is null OR t.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.provinceId} is null OR t.province.id = :#{#criteria.provinceId}) "
    )
    Page<TownEntity> findAllByCriteria(TownCriteria criteria, Pageable pageable);
}
