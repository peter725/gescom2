package es.consumo.junta_arbitral.modules.arbitration.service;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.arbitration.model.criteria.ArbitrationCriteria;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.AdmissionDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.ArbitrationDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.ReassignDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ArbitrationService extends CrudService<ArbitrationEntity, Long> {

    Page<ArbitrationEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbitrationCriteria> wrapper);

    Page<ArbitrationEntity.SimpleProjection> findAllSimpleEntityAndArbitrationBoard(CriteriaWrapper<ArbitrationCriteria> wrapper, LoginEntity login);

    ArbitrationEntity update(ReassignDTO reassignDTO);

    StatusChangeDTO changeStatus(LoginEntity loginUser, long requestId, long statusId, Map<String, Object> requestChangeStatus);

    ArbitrationEntity switchStatus(ChangeStatusDTO changeStatusDTO);

    ArbitrationEntity admission(AdmissionDTO admissionDTO);

    ArbitrationEntity verify(ArbitrationDTO arbitrationDTO);
}
