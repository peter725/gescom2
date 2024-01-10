package es.consumo.junta_arbitral.jwt.rest.user.db.repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JJAARepository<UserEntity, Long> {


    Optional<UserEntity> findByDni(String dni);
}
