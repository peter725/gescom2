package es.consumo.junta_arbitral.modules.arbitration.service;

import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;

public interface StateMachineService {

    ArbitrationEntity sendEvent(long requestId, Message<String> message);

    StateMachine<RequestStatus, String> getStateMachine(long requestId);

    ArbitrationEntity changeTo(LoginEntity loginUser, ArbitrationEntity request, RequestStatus status);

}
