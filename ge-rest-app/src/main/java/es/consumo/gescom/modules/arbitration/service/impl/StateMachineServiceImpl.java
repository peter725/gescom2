package es.consumo.gescom.modules.arbitration.service.impl;

import es.consumo.gescom.commons.exception.CustomValidationException;
import es.consumo.gescom.modules.arbitration.model.constants.RequestStatus;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.arbitration.service.StateMachineService;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class StateMachineServiceImpl implements StateMachineService {


    private final StateMachineFactory<RequestStatus, String> stateMachineFactory;
    private final ArbitrationRepository requestRepository;

    public StateMachineServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                                   StateMachineFactory<RequestStatus, String> stateMachineFactory,
                                   ArbitrationRepository requestRepository) {
        this.stateMachineFactory = stateMachineFactory;
        this.requestRepository = requestRepository;
    }

    @Override
    public ArbitrationEntity sendEvent(long requestId, Message<String> message) {
        final StateMachine<RequestStatus, String> stateMachine = getStateMachine(requestId);

        final boolean result = stateMachine.sendEvent(message);

        return retrieveEvent(stateMachine, requestId, result);
    }

    @Override
    public StateMachine<RequestStatus, String> getStateMachine(long requestId) {

        return requestRepository.findById(requestId) //returns Optional
                .map(order -> {
                    final StateMachine<RequestStatus, String> sm = stateMachineFactory.getStateMachine(Long.toString(requestId));
                    sm.stop();
                    rehydrateState(sm, sm.getExtendedState(), order.getStatus().getCode());
                    sm.start();
                    return sm;
                })
                .orElseThrow(CustomValidationException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArbitrationEntity changeTo(LoginEntity loginUser, ArbitrationEntity request, RequestStatus status) {
        final Map<String, Object> headers = new HashMap<>();
        headers.put("redisEntity", loginUser);
        headers.put("requestEntity", request);

        final String event = getEventName(request.getStatus().getCode(), status);

        final Message<String> message = new GenericMessage<>(event, headers);
        return sendEvent(request.getId(), message);
    }

    private String getEventName(RequestStatus status, RequestStatus next) {
        return String.format("%s_%s", status.name(), next.name());
    }

    private ArbitrationEntity retrieveEvent(StateMachine<RequestStatus, String> stateMachine, long requestId, boolean result) {
        if (stateMachine.hasStateMachineError()) {
            if (stateMachine.getExtendedState().getVariables().containsKey("error")) {
                throw (RuntimeException) stateMachine.getExtendedState().getVariables().get("error");
            } else {
                throw new CustomValidationException();
            }
        }
        if (!result) {
            throw new CustomValidationException("cambio de estado no valido");
        }
        return requestRepository.findById(requestId).orElseThrow();
    }

    private void rehydrateState(StateMachine<RequestStatus, String> newStateMachine, ExtendedState extendedState, RequestStatus orderStatus) {
        newStateMachine.getStateMachineAccessor().doWithAllRegions(sma ->
                sma.resetStateMachine(new DefaultStateMachineContext<>(orderStatus, null, null, extendedState))
        );
    }

}
