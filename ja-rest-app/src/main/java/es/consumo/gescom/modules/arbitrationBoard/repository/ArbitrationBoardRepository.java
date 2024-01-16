package es.consumo.gescom.modules.arbitrationBoard.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitrationBoardRepository extends GESCOMRepository<ArbitrationBoardEntity, Long>,
        QueryByCriteria<ArbitrationBoardEntity.SimpleProjection, ArbitrationBoardCriteria> {
    @Query(value = "SELECT a FROM ArbitrationBoardEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.email} is null OR a.email LIKE :#{#criteria.email}) "
            + "AND (:#{#criteria.phone} is null OR a.phone LIKE :#{#criteria.phone}) "
            + "AND (:#{#criteria.search} is null OR a.name like  :#{#criteria.search} OR a.email like  :#{#criteria.search} OR a.phone like  :#{#criteria.search}) "

    )
    Page<ArbitrationBoardEntity.SimpleProjection> findAllByCriteria(ArbitrationBoardCriteria criteria, Pageable pageable);

    @Query("SELECT count(a.id)>0 FROM ArbitrationBoardEntity a WHERE LOWER(a.name)=LOWER(:name) AND  a.id <>:id")
    boolean existsArbitrationBoardEntityByName(Long id, String name);

}
