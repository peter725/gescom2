package es.consumo.junta_arbitral.modules.document.repository;

import es.consumo.junta_arbitral.modules.document.model.criteria.DocumentTypeCriteria;
import es.consumo.junta_arbitral.modules.document.model.entity.DocumentTypeEntity;
import es.consumo.junta_arbitral.commons.db.repository.QueryByCriteria;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JJAARepository<DocumentTypeEntity, Long>,
        QueryByCriteria<DocumentTypeEntity, DocumentTypeCriteria> {

    @Override
    @Query(value = "SELECT t FROM DocumentTypeEntity t "
            + "WHERE "
            + "(:#{#criteria.name} is null OR t.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.required} is null OR t.required = :#{#criteria.required}) "
    )
    Page<DocumentTypeEntity> findAllByCriteria(DocumentTypeCriteria criteria, Pageable pageable);
}
