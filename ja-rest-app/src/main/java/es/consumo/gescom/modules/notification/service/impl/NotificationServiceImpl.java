package es.consumo.gescom.modules.notification.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.notification.model.entity.NotificationEntity;
import es.consumo.gescom.modules.notification.service.NotificationService;

public class NotificationServiceImpl  extends EntityCrudService<NotificationEntity, Long> implements NotificationService {

    protected NotificationServiceImpl(GESCOMRepository<NotificationEntity, Long> repository) {
        super(repository);
    }

}
