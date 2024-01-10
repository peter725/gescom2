package es.consumo.junta_arbitral.modules.arbitration.statemachine;

import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

@Log4j2
public abstract class AbstractStateMachine<T> implements Action<RequestStatus, String> {

    protected final ArbitrationRepository repository;
    protected final ArbitrationStatusRepository statusRepository;

    public AbstractStateMachine(ArbitrationRepository repository, ArbitrationStatusRepository statusRepository) {
        this.repository = repository;
        this.statusRepository = statusRepository;
    }

    /**
     * Execute action with a {@link StateContext}.
     *
     * @param context the state context
     */
    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void execute(StateContext<RequestStatus, String> context) {
        try {
            final Message<String> message = context.getMessage();
            final ArbitrationEntity requestEntity = message.getHeaders().get("requestEntity", ArbitrationEntity.class);
            final LoginEntity redisEntity = message.getHeaders().get("loginEntity", LoginEntity.class);
            T form = getInstance(message.getHeaders());
            execute(redisEntity, requestEntity, form);
            updateStatus(redisEntity, requestEntity, context.getTarget().getId());
        } catch (Exception ex) {
            context.getStateMachine().setStateMachineError(ex);
            context.getExtendedState().getVariables().put("error", ex);
            throw ex;
        }
    }

    protected void updateStatus(LoginEntity redisEntity, ArbitrationEntity requestEntity, RequestStatus requestStatus) {
        ArbitrationStatusEntity status = statusRepository.findByCode(requestStatus).orElseThrow();
        requestEntity.setStatus(status);

        repository.save(requestEntity);
    }

    protected abstract void execute(LoginEntity redisEntity, ArbitrationEntity requestEntity, T form);

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Class<T> getClassDTO() {
        Class<T> returnType;
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        try {
            returnType = (Class<T>) type.getActualTypeArguments()[0];
        } catch (ClassCastException ex) {
            final TypeVariable typeVariable = (TypeVariable) type.getActualTypeArguments()[0];
            returnType = (Class<T>) typeVariable.getBounds()[0];
        }

        return returnType;
    }

    protected T getInstance(MessageHeaders headers) {
        T form;
        try {
            final ModelMapper mapperUtil = new ModelMapper();
            form = mapperUtil.map(headers, this.getClassDTO());
        } catch (Exception ex) {
            log.error(ex);
            form = null;
        }
        return form;
    }


}
