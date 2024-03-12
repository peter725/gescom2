package es.consumo.gescom.modules.document.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends GESCOMRepository<DocumentEntity, Long>,
        QueryByCriteria<DocumentEntity, DocumentCriteria> {
    @Override
    @Query(value = "SELECT t FROM DocumentEntity t "
            + "WHERE t.campaignId = :#{#criteria.campaignId}  "
            + "AND t.documentType.required = :#{#criteria.documentTypeRequired}  "
    )
    Page<DocumentEntity> findAllByCriteria(DocumentCriteria criteria, Pageable pageable);

    @Query(value = "SELECT t FROM DocumentEntity t WHERE t.campaignId = :idCampaign " )
    List<DocumentEntity> findDocumentByCampaignId(@Param("idCampaign") Long idCampaign);
}
