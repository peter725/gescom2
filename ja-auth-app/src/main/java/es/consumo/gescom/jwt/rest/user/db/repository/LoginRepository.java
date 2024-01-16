package es.consumo.gescom.jwt.rest.user.db.repository;


import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends GESCOMRepository<LoginEntity, Long> {

    @Query("SELECT u FROM LoginEntity u where lower(u.username) = lower(:username)")
    Optional<LoginEntity> findByUsername(String username);
}
