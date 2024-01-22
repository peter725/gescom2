package es.consumo.gescom.modules.autonomousCommunityParticipants.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.criteria.AutonomousCommunityParticipantsCriteria;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.dto.AutonomousCommunityParticipantsDTO;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunityParticipantsRepository extends GESCOMRepository<AutonomousCommunityParticipantsEntity, Long>, QueryByCriteria<AutonomousCommunityParticipantsEntity.SimpleProjection, AutonomousCommunityParticipantsCriteria> {

    @Query("SELECT p FROM AutonomousCommunityParticipantsEntity p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunityParticipantsEntity> findByIdCampaign(@Param("campaignId") Long idCampaign);
}
