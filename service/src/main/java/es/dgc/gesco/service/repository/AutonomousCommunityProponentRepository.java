package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunityProponent.db.entity.AutonomousCommunityProponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunityProponentRepository extends BaseRepository<AutonomousCommunityProponent, Long>{

    @Override
    Page<AutonomousCommunityProponent> findAll(@Param("pageable") Pageable pageable);

    @Query("SELECT p FROM AutonomousCommunityProponent p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunityProponent> findByCampaignId(@Param("campaignId") Long campaignId);
}
