package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalAuthorityRepository extends BaseRepository<AuthorityOEU, Long>{
}
