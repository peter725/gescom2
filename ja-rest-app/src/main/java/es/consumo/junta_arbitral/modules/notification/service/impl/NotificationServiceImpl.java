package es.consumo.junta_arbitral.modules.notification.service.impl;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.modules.notification.model.entity.NotificationEntity;
import es.consumo.junta_arbitral.modules.notification.service.NotificationService;

public class NotificationServiceImpl  extends EntityCrudService<NotificationEntity, Long> implements NotificationService {

    protected NotificationServiceImpl(JJAARepository<NotificationEntity, Long> repository) {
        super(repository);
    }

}
