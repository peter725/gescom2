package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutonomousCommunityParticipantRepository extends BaseRepository<AutonomousCommunityParticipants, Long>{

    @Override
    Page<AutonomousCommunityParticipants> findAll(@Param("pageable") Pageable pageable);
}
