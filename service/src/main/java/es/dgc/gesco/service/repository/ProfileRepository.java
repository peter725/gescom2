package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends BaseRepository<Profile, Long>{
}
