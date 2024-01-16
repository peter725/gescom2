package es.consumo.gescom.modules.arbitration.service;

import es.consumo.gescom.commons.service.ReadService;
import es.consumo.gescom.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;

import java.util.Map;

public interface RequestStatusService extends ReadService<ArbitrationStatusEntity, Long> {

    StatusChangeDTO sendEvent(LoginEntity loginUser, ArbitrationEntity request, long nextId,
                              Map<String, Object> requestChangeStatus);

}
