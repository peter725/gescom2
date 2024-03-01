package es.consumo.gescom.modules.protocol_results.repository;

import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

import java.util.List;

@Repository
public interface ProtocolResultsRepository extends GESCOMRepository<ProtocolResultsEntity, Long> {

    @Query(value = "SELECT h FROM ProtocolResultsEntity h where h.id = :id ")
    Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT h FROM ProtocolResultsEntity h where h.campaignId = :campaignId ")
    List<ProtocolResultsEntity> findAllByCampaignId(@Param("campaignId") Long campaignId);

}
