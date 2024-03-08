package es.consumo.gescom.modules.ipr.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.ipr.model.dto.IprResponseDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IprRepository extends GESCOMRepository<IprEntity, Long> {

    @Query(value = "SELECT h FROM IprEntity h where h.id = :id ")
    Page<IprEntity.SimpleProjection> findAllIprById(Pageable pageable, @Param("id") Long id);


   /* @Query(value = """
            SELECT new es.consumo.gescom.modules.ipr.model.dto.IprResponseDTO(
            ipr.id,
            ipr.iprId,
            ipr.iprCode,
            ipr.protocolCode,
            ipr.iprName,
            ipr.campaignId,
            ipr.protocolId,
            iq.iprQuestionId,
            iq.iprQuestionCode,
            iq.orderQuestion,
            iq.percentageRespectTo,
            iq.formula,
            iq.question
            )
            ipr, iq FROM IprEntity AS ipr 
            JOIN IprQuestionEntity AS iq ON ipr.code = iq.iprCode 
            WHERE ipr.campaignId =:campaignId 
            AND ipr.protocolCode =: protocolCode
            """)
    List<IprResponseDTO> findAllIprByCampaignIdAndProtocolCode(@Param("campaignId") Long campaignId, @Param("protocolCode") String protocolCode);*/

    @Query(value = """
        SELECT new es.consumo.gescom.modules.ipr.model.dto.IprResponseDTO(
        ipr.id,
        ipr.code,
        ipr.protocolCode,
        ipr.name,
        ipr.campaignId,
        ipr.protocolId,
        iq.orderQuestion,
        iq.percentageRespectTo,
        iq.formula,
        iq.question
        )
        FROM IprEntity ipr
        JOIN IprQuestionEntity iq ON ipr.code = iq.iprCode
        WHERE ipr.campaignId =:campaignId
        AND ipr.protocolCode =:protocolCode
        AND ipr.code =:iprcode
        """)
    List<IprResponseDTO> findAllIprByCampaignIdAndProtocolCode(@Param("campaignId") Long campaignId, @Param("protocolCode") String protocolCode, @Param("iprcode") String iprCode);

    @Query(value = "SELECT ipr FROM IprEntity ipr WHERE ipr.campaignId =:campaignId AND ipr.protocolCode =:protocolCode")
    List<IprEntity> findAllByCampaignAndProtocolCode(@Param("campaignId") Long campaignId, @Param("protocolCode") String protocolCode);


    @Query(value = "SELECT ipr, iq FROM IprEntity AS ipr JOIN IprQuestionEntity AS iq ON ipr.id = iq.iprId.id WHERE ipr.campaignId =:campaignId AND ipr.protocolId =:protocolId")
    List<IprResponseDTO> findAllIprByCampaignIdAndProtocolId(@Param("campaignId") Long campaignId, @Param("protocolId") Long protocolId);

}
