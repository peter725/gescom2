package es.consumo.gescom.modules.arbitration.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.arbitration.model.criteria.ArbitrationCriteria;
import es.consumo.gescom.modules.arbitration.model.dto.AdmissionDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ArbitrationDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ReassignDTO;
import es.consumo.gescom.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
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
