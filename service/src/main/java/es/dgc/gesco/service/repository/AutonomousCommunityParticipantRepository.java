package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunityParticipantRepository extends BaseRepository<AutonomousCommunityParticipants, Long>{

    @Override
    Page<AutonomousCommunityParticipants> findAll(@Param("pageable") Pageable pageable);

    @Query("SELECT p FROM AutonomousCommunityParticipants p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunityParticipants> findByCampaignId(@Param("campaignId") Long campaignId);
}
