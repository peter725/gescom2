package es.consumo.gescom.modules.document.repository;

import es.consumo.gescom.modules.document.model.criteria.DocumentTypeCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentTypeEntity;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends GESCOMRepository<DocumentTypeEntity, Long>,
        QueryByCriteria<DocumentTypeEntity, DocumentTypeCriteria> {

    @Override
    @Query(value = "SELECT t FROM DocumentTypeEntity t "
            + "WHERE "
            + "(:#{#criteria.name} is null OR t.name LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.required} is null OR t.required = :#{#criteria.required}) "
    )
    Page<DocumentTypeEntity> findAllByCriteria(DocumentTypeCriteria criteria, Pageable pageable);
}
