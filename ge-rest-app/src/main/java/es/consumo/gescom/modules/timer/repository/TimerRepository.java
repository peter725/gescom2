package es.consumo.gescom.modules.timer.repository;

import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.timer.model.entity.TimerEntity;
import java.util.Optional;

@Repository
public interface TimerRepository extends GESCOMRepository<TimerEntity, Long> {
    Optional<TimerEntity> findById(Long id);
}
