package es.consumo.gescom.modules.arbiter.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.arbiter.model.criteria.CollegiateTypeCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.CollegiateTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegiateTypeRepository extends JpaRepository<CollegiateTypeEntity, Long> ,
        QueryByCriteria<CollegiateTypeEntity.SimpleProjection, CollegiateTypeCriteria> {
    
    @Query(value = "SELECT c FROM CollegiateTypeEntity c "
            + "LEFT JOIN c.arbiterTypeEntity at "
            + "WHERE at.id = :#{#criteria.idArbiterType}"
    )
    Page<CollegiateTypeEntity.SimpleProjection> findAllByCriteria(CollegiateTypeCriteria criteria, Pageable pageable);
}

