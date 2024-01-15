package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.modules.arbitration.model.entity.NotificationTypeEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JJAARepository<NotificationTypeEntity, Long> {
}
