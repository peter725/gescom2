package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunitySpecialist.db.entity.AutonomousCommunitySpecialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunitySpecialistRepository extends BaseRepository<AutonomousCommunitySpecialist, Long>{

    @Override
    Page<AutonomousCommunitySpecialist> findAll(@Param("pageable") Pageable pageable);

    @Query("SELECT p FROM AutonomousCommunitySpecialist p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunitySpecialist> findByCampaignId(@Param("campaignId") Long campaignId);
}
