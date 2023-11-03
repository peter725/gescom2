package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationalAuthorityRepository extends BaseRepository<AuthorityOEU, Long>{
}
