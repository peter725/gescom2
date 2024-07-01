package es.consumo.gescom.modules.protocol.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDetailDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/protocol")
@Tag(name = "Protocol controller")
public class ProtocolController extends AbstractCrudController<ProtocolEntity, ProtocolDTO, Long, ProtocolCriteria> {

    @Autowired
    public ProtocolController(ProtocolService service,
                              DataConverter<ProtocolEntity, ProtocolDTO> dataConverter) {
        super(service, dataConverter);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<Page<ProtocolEntity>> findListByCriteria(ProtocolCriteria protocolCriteria,
                                                                   @RequestParam(name = "protocol", required = false) String protocol,
                                                                   @RequestParam(name = "code", required = false) String code) {
        Page<ProtocolEntity> result =
                ((ProtocolService) service).getProtocolByNameOrCode(new CriteriaWrapper<>(protocolCriteria), protocol, code);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<List<ProtocolDTO>> findListByCriteria(@PathVariable  Long id) {
        List<ProtocolDTO> result =
                ((ProtocolService) service).findProtocolByCampaignId(id);
        return ResponseEntity.ok(result);
    }

    @Override
    protected ProtocolDTO performCreate(ProtocolDTO payload) {
        return ((ProtocolService) service).createProtocol(payload);
    }

    @GetMapping("/protocolo/{id}")
    protected ProtocolDetailDTO findListByProtocol(@PathVariable Long id) {
        return ((ProtocolService) service).findProtocolDetailById(id);
    }

    @GetMapping("/code")
    protected List<QuestionsDTO> findListByProtocol(ProtocolDTO protocolDTO) {
        return ((ProtocolService) service).findProtocolByIdOrCode(protocolDTO);
    }

    @Override
    public Optional<?> performFindById(Long id) {
        return Optional.of(
                ((ProtocolService) service).findProtocolDTOById(id)
        );
    }

    @Override
    public ProtocolDTO performUpdate(Long id, ProtocolDTO payload) {
        return  ((ProtocolService) service).updateProtocol(id, payload);
    }
}
