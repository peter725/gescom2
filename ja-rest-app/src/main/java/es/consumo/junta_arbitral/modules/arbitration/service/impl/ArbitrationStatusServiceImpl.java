package es.consumo.junta_arbitral.modules.arbitration.service.impl;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityReadService;
import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.modules.arbitration.service.RequestStatusService;
import es.consumo.junta_arbitral.modules.arbitration.service.StateMachineService;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author serikat
 */
@Service
public class ArbitrationStatusServiceImpl extends EntityReadService<ArbitrationStatusEntity, Long> implements RequestStatusService {
    private final StateMachineService stateMachineService;

    @Autowired
    public ArbitrationStatusServiceImpl(JJAARepository<ArbitrationStatusEntity, Long> repository,
                                        StateMachineService stateMachineService) {
        super(repository);
        this.stateMachineService = stateMachineService;
    }

    @Override
    public StatusChangeDTO sendEvent(LoginEntity loginUser, ArbitrationEntity request, long statusId,
                                     Map<String, Object> requestChangeStatus) {
        final Map<String, Object> headers = new HashMap<>();
        headers.put("loginEntity", loginUser);
        headers.put("requestEntity", request);
        headers.putAll(requestChangeStatus);

        ArbitrationStatusEntity status=findById(statusId).orElseThrow();

        String event = status.getCode().equals(RequestStatus.ARCHIVED)
                ? RequestStatus.ARCHIVED.name()
                : String.join("_", request.getStatus().getCode().name(), status.getCode().name());

        final Message<String> message = new GenericMessage<>(event, headers);
        final ArbitrationEntity entity = stateMachineService.sendEvent(request.getId(), message);

        return null;
    }

}
