package es.consumo.junta_arbitral.modules.arbitration.controller;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.NotificationTypeEntity;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractReadOnlyController;
import es.consumo.junta_arbitral.commons.db.repository.ReadOnlyRepository;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/notification-type")
@Tag(name = "Notification Type controller")
public class NotificationTypeController extends AbstractReadOnlyController<NotificationTypeEntity, Long, FilterCriteria> {
    @Autowired
    public NotificationTypeController(ReadOnlyRepository<NotificationTypeEntity, Long> repository) {
        super(repository);
    }
}
