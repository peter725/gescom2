package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutonomousCommunityRepository extends BaseRepository<AutonomousCommunity, Long>{

    @Override
    Page<AutonomousCommunity> findAll(@Param("pageable") Pageable pageable);
}
