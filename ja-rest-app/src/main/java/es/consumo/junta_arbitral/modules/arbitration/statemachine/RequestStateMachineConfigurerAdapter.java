package es.consumo.junta_arbitral.modules.arbitration.statemachine;


import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatusEventConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Set;

@Configuration
@EnableStateMachineFactory
public class RequestStateMachineConfigurerAdapter extends StateMachineConfigurerAdapter<RequestStatus, String> {

    private final ApplicationContext context;
    private final StateMachineEventListenerAdapter stateMachineEventListenerAdapter;

    @Autowired
    public RequestStateMachineConfigurerAdapter(ApplicationContext context,
                                                StateMachineEventListenerAdapter stateMachineEventListenerAdapter) {
        this.context = context;
        this.stateMachineEventListenerAdapter = stateMachineEventListenerAdapter;
    }

    @Override
    public void configure(StateMachineStateConfigurer<RequestStatus, String> states)
            throws Exception {
        states
                .withStates()
                .initial(RequestStatus.CREATED)
                .states(Set.of(RequestStatus.values()));

    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<RequestStatus, String> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(stateMachineEventListenerAdapter);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<RequestStatus, String> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(RequestStatus.DRAFT)
                .target(RequestStatus.SIGNED)
                .event(RequestStatusEventConstant.EVENT_DRAFT_FIRM)
                .action(getAction("draftToFirmEvent"));

    }

    private Action<RequestStatus, String> getAction(String name) {
        //noinspection unchecked
        return context.getBean(name, Action.class);
    }

}
