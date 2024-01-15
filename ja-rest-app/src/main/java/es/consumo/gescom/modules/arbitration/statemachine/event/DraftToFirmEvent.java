package es.consumo.gescom.modules.arbitration.statemachine.event;


import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.gescom.modules.arbitration.statemachine.AbstractStateMachine;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.stereotype.Component;

@Component
public class DraftToFirmEvent extends AbstractStateMachine<DraftToFirmEvent> {
    public DraftToFirmEvent(ArbitrationRepository repository, ArbitrationStatusRepository statusRepository) {
        super(repository, statusRepository);
    }

    @Override
    protected void execute(LoginEntity redisEntity, ArbitrationEntity requestEntity, DraftToFirmEvent form) {
        System.out.println("aqui");
    }

}
