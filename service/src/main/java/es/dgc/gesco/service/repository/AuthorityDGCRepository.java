package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.authorityDGC.db.entity.AuthorityDGC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDGCRepository extends BaseRepository<AuthorityDGC, Long>{

    @Override
    Page<AuthorityDGC> findAll(@Param("pageable") Pageable pageable);
}