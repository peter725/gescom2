package es.consumo.gescom.modules.arbitration.statemachine;

import es.consumo.gescom.modules.arbitration.model.constants.RequestStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StateMachineEventListenerAdapter extends StateMachineListenerAdapter<RequestStatus, String> {

    @Override
    public void eventNotAccepted(Message<String> event) {
        //LOG which event was not accepted etc.
        log.error(event);
    }

}
