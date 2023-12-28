package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunityProponent.db.entity.AutonomousCommunityProponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutonomousCommunityProponentRepository extends BaseRepository<AutonomousCommunityProponent, Long>{

    @Override
    Page<AutonomousCommunityProponent> findAll(@Param("pageable") Pageable pageable);
}
