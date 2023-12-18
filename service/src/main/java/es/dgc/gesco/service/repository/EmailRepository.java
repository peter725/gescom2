package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.email.db.entity.Email;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends BaseRepository<Email, Long>{

    @Override
    List<Email> findAll();
}
