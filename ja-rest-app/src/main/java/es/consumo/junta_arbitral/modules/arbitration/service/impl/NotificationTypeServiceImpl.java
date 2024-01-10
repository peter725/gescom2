package es.consumo.junta_arbitral.modules.arbitration.service.impl;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.NotificationTypeEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class NotificationTypeServiceImpl extends EntityReadService<NotificationTypeEntity, Long> {
    @Autowired
    public NotificationTypeServiceImpl(JJAARepository<NotificationTypeEntity, Long> repository) {
        super(repository);
    }
}
