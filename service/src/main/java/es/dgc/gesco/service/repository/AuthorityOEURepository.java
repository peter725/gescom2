package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityOEURepository extends BaseRepository<AuthorityOEU, Long>{
    @Override
    Page<AuthorityOEU> findAll(@Param("pageable") Pageable pageable);
}
