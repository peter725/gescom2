package es.consumo.gescom.modules.arbiter.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.arbiter.model.criteria.ArbiterCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.ArbiterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbiterRepository extends GESCOMRepository<ArbiterEntity, Long>,
        QueryByCriteria<ArbiterEntity.SimpleProjection, ArbiterCriteria> {
    @Query(value = "SELECT a FROM ArbiterEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.surnames} is null OR a.surnames LIKE :#{#criteria.surnames}) "
            + "AND (:#{#criteria.nif} is null OR a.nif LIKE :#{#criteria.nif}) "
            + "AND (:#{#criteria.search} is null OR a.name like  :#{#criteria.search} OR a.surnames like  :#{#criteria.search} OR a.nif like  :#{#criteria.search}) "

    )
    Page<ArbiterEntity.SimpleProjection> findAllByCriteria(ArbiterCriteria criteria, Pageable pageable);

    @Query("SELECT count(a.id)>0 FROM ArbiterEntity a WHERE LOWER(a.name)=LOWER(:name) AND  a.id <>:id")
    boolean existsCollegiateTypeEntityByName(Long id, String name);


}
