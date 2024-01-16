package es.consumo.gescom.modules.arbitration.service;

import es.consumo.gescom.modules.arbitration.model.constants.RequestStatus;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;

public interface StateMachineService {

    ArbitrationEntity sendEvent(long requestId, Message<String> message);

    StateMachine<RequestStatus, String> getStateMachine(long requestId);

    ArbitrationEntity changeTo(LoginEntity loginUser, ArbitrationEntity request, RequestStatus status);

}
