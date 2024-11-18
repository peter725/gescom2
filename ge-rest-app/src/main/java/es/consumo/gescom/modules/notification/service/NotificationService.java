package es.consumo.gescom.modules.notification.service;

import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.notification.model.entity.NotificationEntity;

@Service
public interface NotificationService extends CrudService<NotificationEntity, Long> {

    
}
