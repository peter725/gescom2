package es.consumo.junta_arbitral.modules.document.repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.db.repository.QueryByCriteria;
import es.consumo.junta_arbitral.modules.document.model.criteria.DocumentCriteria;
import es.consumo.junta_arbitral.modules.document.model.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JJAARepository<DocumentEntity, Long>,
        QueryByCriteria<DocumentEntity, DocumentCriteria> {
    @Override
    @Query(value = "SELECT t FROM DocumentEntity t "
            + "WHERE t.arbitrationId = :#{#criteria.arbitrationId}  "
            + "AND t.documentType.required = :#{#criteria.documentTypeRequired}  "
    )
    Page<DocumentEntity> findAllByCriteria(DocumentCriteria criteria, Pageable pageable);

}
