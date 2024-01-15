package es.consumo.gescom.modules.timer.repository;

import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.modules.timer.model.entity.TimerEntity;
import java.util.Optional;

@Repository
public interface TimerRepository extends JJAARepository<TimerEntity, Long> {
    Optional<TimerEntity> findById(Long id);
}
