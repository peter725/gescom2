package es.consumo.gescom.modules.arbitration.statemachine;

import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;

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
