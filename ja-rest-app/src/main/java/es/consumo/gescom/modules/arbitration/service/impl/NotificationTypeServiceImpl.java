package es.consumo.gescom.modules.arbitration.service.impl;

import es.consumo.gescom.modules.arbitration.model.entity.NotificationTypeEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class NotificationTypeServiceImpl extends EntityReadService<NotificationTypeEntity, Long> {
    @Autowired
    public NotificationTypeServiceImpl(GESCOMRepository<NotificationTypeEntity, Long> repository) {
        super(repository);
    }
}
