package es.consumo.junta_arbitral.modules.notification.service;

import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.notification.model.entity.NotificationEntity;

@Service
public interface NotificationService extends CrudService<NotificationEntity, Long> {

    
}
