package es.consumo.gescom.modules.document.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends GESCOMRepository<DocumentEntity, Long>,
        QueryByCriteria<DocumentEntity, DocumentCriteria> {
    @Override
    @Query(value = "SELECT t FROM DocumentEntity t "
            + "WHERE t.campaignId = :#{#criteria.campaignId}  "
            + "AND t.documentType.required = :#{#criteria.documentTypeRequired}  "
    )
    Page<DocumentEntity> findAllByCriteria(DocumentCriteria criteria, Pageable pageable);

}
