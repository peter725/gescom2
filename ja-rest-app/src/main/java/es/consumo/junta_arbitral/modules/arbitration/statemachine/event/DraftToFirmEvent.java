package es.consumo.junta_arbitral.modules.arbitration.statemachine.event;


import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.junta_arbitral.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.junta_arbitral.modules.arbitration.statemachine.AbstractStateMachine;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
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
