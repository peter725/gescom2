package es.consumo.junta_arbitral.modules.timer.repository;

import org.springframework.stereotype.Repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.modules.timer.model.entity.TimerEntity;
import java.util.Optional;

@Repository
public interface TimerRepository extends JJAARepository<TimerEntity, Long> {
    Optional<TimerEntity> findById(Long id);
}
