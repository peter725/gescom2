package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunitySpecialist.db.entity.AutonomousCommunitySpecialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutonomousCommunitySpecialistRepository extends BaseRepository<AutonomousCommunitySpecialist, Long>{

    @Override
    Page<AutonomousCommunitySpecialist> findAll(@Param("pageable") Pageable pageable);
}
