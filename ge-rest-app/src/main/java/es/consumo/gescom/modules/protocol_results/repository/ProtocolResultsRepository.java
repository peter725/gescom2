package es.consumo.gescom.modules.protocol_results.repository;

import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsResponseDTO;
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

    @Query(value = """
            SELECT new es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsResponseDTO(
            pr.id,
            pr.autonomousCommunityCountryCode,
            pr.productServiceCode,
            pr.campaignId,
            pr.protocolCode,
            pr.productServiceId,
            pr.protocolId,
            pr.autonomousCommunityCountryId,
            pr.code,
            tpr.ccaaRen,
            tpr.ccaaRep,
            tpr.ccaaRes,
            tpr.codeQuestion,
            tpr.protocolResultsId
            )
            FROM ProtocolResultsEntity pr
            JOIN TotalProtocolResultsEntity tpr ON pr.code = tpr.protocolResultsCode
            WHERE pr.campaignId =:campaignId
            AND pr.protocolCode =:protocolCode
            AND pr.productServiceCode =:productServiceCode
            """)
    List<ProtocolResultsResponseDTO> findProtocolResultsByCampaignIdAndProtocolCode(@Param("campaignId") Long campaignId, @Param("protocolCode") String protocolCode, @Param("productServiceCode") String productServiceCode);

    @Query(value = """
            SELECT new es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsResponseDTO(
            pr.id,
            pr.autonomousCommunityCountryCode,
            pr.productServiceCode,
            pr.campaignId,
            pr.protocolCode,
            pr.productServiceId,
            pr.protocolId,
            pr.autonomousCommunityCountryId,
            pr.code,
            tpr.ccaaRen,
            tpr.ccaaRep,
            tpr.ccaaRes,
            tpr.codeQuestion,
            tpr.protocolResultsId
            )
            FROM ProtocolResultsEntity AS pr
            JOIN TotalProtocolResultsEntity tpr ON pr.id = tpr.protocolResultsId
            WHERE pr.campaignId =:campaignId
            AND pr.protocolId =:protocolId
            AND pr.productServiceCode =:productServiceCode""")
    List<ProtocolResultsResponseDTO> findProtocolResultsByCampaignIdAndProtocolId(@Param("campaignId") Long campaignId, @Param("protocolId") Long protocolId, @Param("productServiceCode") String productServiceCode);

}
