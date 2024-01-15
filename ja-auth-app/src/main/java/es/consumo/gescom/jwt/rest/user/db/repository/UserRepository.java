package es.consumo.gescom.jwt.rest.user.db.repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.jwt.rest.user.db.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JJAARepository<UserEntity, Long> {


    Optional<UserEntity> findByDni(String dni);
}
