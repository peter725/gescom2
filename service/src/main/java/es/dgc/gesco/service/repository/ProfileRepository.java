package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends BaseRepository<Profile, Long>{

    @Override
    Page<Profile> findAll(@Param("pageable") Pageable pageable);
}
