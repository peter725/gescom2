package es.consumo.junta_arbitral.modules.arbitration.statemachine;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;

public abstract class SimpleStateMachineEvent extends AbstractStateMachine<Object> {

    public SimpleStateMachineEvent(ArbitrationRepository repository,
                                   ArbitrationStatusRepository statusRepository) {
        super(repository, statusRepository);
    }

    @Override
    public void execute(LoginEntity redisEntity, ArbitrationEntity requestEntity, Object form) {
        final long userId = redisEntity.getId();


        executePostValidAssign(redisEntity, requestEntity, form);




    }

    protected void executePostValidAssign(LoginEntity redisEntity, ArbitrationEntity requestEntity, Object form) {
    }
}
