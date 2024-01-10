package es.consumo.junta_arbitral.modules.arbitration.service;

import es.consumo.junta_arbitral.commons.service.ReadService;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;

import java.util.Map;

public interface RequestStatusService extends ReadService<ArbitrationStatusEntity, Long> {

    StatusChangeDTO sendEvent(LoginEntity loginUser, ArbitrationEntity request, long nextId,
                              Map<String, Object> requestChangeStatus);

}
