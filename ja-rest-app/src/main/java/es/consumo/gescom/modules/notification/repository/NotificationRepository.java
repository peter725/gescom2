package es.consumo.gescom.modules.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.modules.notification.model.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends JJAARepository<NotificationEntity, Long> {

    @Query("SELECT n FROM NotificationEntity n WHERE n.process = :process AND n.arbitrationEntity.id = :arbitrationId")
    List<NotificationEntity> findByRecipientAndProcessAndArbitrationId(
            @Param("process") String process,
            @Param("arbitrationId") Long arbitrationId
    );

}