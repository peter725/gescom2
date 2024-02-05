package es.consumo.gescom.jwt.rest.user.db.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.jwt.rest.user.db.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GESCOMRepository<UserEntity, Long> {


    Optional<UserEntity> findByDni(String dni);


    Optional<UserEntity> findByLoginId(Long idLogin);
}
