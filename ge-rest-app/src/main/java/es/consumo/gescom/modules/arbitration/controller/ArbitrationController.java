package es.consumo.gescom.modules.arbitration.controller;


import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.exception.CustomValidationException;
import es.consumo.gescom.modules.arbitration.model.criteria.ArbitrationCriteria;
import es.consumo.gescom.modules.arbitration.model.dto.AdmissionDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ArbitrationDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ReassignDTO;
import es.consumo.gescom.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.service.ArbitrationService;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/arbitration")
@Tag(name = "Arbitration controller")
public class ArbitrationController extends AbstractCrudController<ArbitrationEntity, ArbitrationDTO, Long, FilterCriteria> {


    @Autowired
    public ArbitrationController(ArbitrationService service,
                                DataConverter<ArbitrationEntity, ArbitrationDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ArbitrationEntity.SimpleProjection>> findListByCriteria(ArbitrationCriteria arbitrationCriteria) {
        Page<ArbitrationEntity.SimpleProjection> result =
                ((ArbitrationService) service).findAllSimpleEntity(new CriteriaWrapper<>(arbitrationCriteria));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/data")
    public ResponseEntity<Page<ArbitrationEntity.SimpleProjection>> findListByCriteriaAndArbitrationBoard(ArbitrationCriteria arbitrationCriteria, @AuthenticationPrincipal LoginEntity loginEntity) {
        Page<ArbitrationEntity.SimpleProjection> result =
                ((ArbitrationService) service).findAllSimpleEntityAndArbitrationBoard(new CriteriaWrapper<>(arbitrationCriteria), loginEntity);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<ArbitrationEntity> modifyArbitration(@RequestBody ReassignDTO reassignDTO) {
        ArbitrationEntity result = ((ArbitrationService) service).update(reassignDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/switch")
    public ResponseEntity<ArbitrationEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus) {
        ArbitrationEntity result = ((ArbitrationService) service).switchStatus(changeStatus);
        return ResponseEntity.ok(result);
    }
    

    @PostMapping("/admission")
    public ResponseEntity<ArbitrationEntity> updateAdmission(@RequestBody AdmissionDTO admissionDTO) {
        ArbitrationEntity result = ((ArbitrationService) service).admission(admissionDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify")
    public ResponseEntity<ArbitrationEntity> verifyArbitration(@RequestBody ArbitrationDTO arbitrationDTO) {
        ArbitrationEntity result = ((ArbitrationService) service).verify(arbitrationDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/status/change")
    public @ResponseBody
    ResponseEntity<StatusChangeDTO> changeStatus(@AuthenticationPrincipal LoginEntity loginEntity,
                                            @RequestBody Map<String, Object> requestChangeStatus) throws Exception {

        final Object requestId = requestChangeStatus.get("requestId");
        if (Objects.isNull(requestId) || StringUtils.isBlank(requestId.toString())) {
            throw new CustomValidationException("id es nulo");
        } else {
            requestChangeStatus.remove("requestId");
        }
        final Object newStatus = requestChangeStatus.get("statusId");
        if (Objects.isNull(newStatus) || StringUtils.isBlank(newStatus.toString())) {
            throw new CustomValidationException("estado es nulo");
        } else {
            requestChangeStatus.remove("statusId");
        }
        StatusChangeDTO dto = ((ArbitrationService) service).changeStatus(loginEntity, Long.parseLong(requestId.toString()),
                Long.parseLong(newStatus.toString()), requestChangeStatus);

        return ResponseEntity.ok(dto);

    }
}
