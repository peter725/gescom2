package es.consumo.gescom.modules.ipr.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.totalProtocolResults.model.criteria.TotalProtocolResultsCriteria;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import es.consumo.gescom.modules.iprQuestion.service.impl.IprQuestionServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/ipr")
@Tag(name = "IPR controller")
public class IprController extends AbstractCrudController<IprEntity, IprDTO, Long, FilterCriteria> {

    @Autowired
    IprQuestionServiceImpl  iprQuestionService;

    @Autowired
    public IprController(IprService service, DataConverter<IprEntity, IprDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<IprEntity.SimpleProjection>> findListByCriteria(IprCriteria iprCriteria, @PathVariable  Long id) {
        Page<IprEntity.SimpleProjection> result =
                ((IprService) service).findAllIprById(new CriteriaWrapper<>(iprCriteria), id);
        return ResponseEntity.ok(result);
    }

    @Override
    protected IprDTO performCreate(IprDTO payload) {
        return ((IprService) service).createIPR(payload);
    }

    @GetMapping("/campaign/{campaignId}/{protocolId}")
    public ResponseEntity<List<IprDTO>> findAllIprByCampaignIdAndProtocolId(@PathVariable Long campaignId, @PathVariable Long protocolId) {
        List<IprDTO> result =
                ((IprService) service).findAllIprByCampaignIdAndProtocolId(campaignId, protocolId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/campaign/{campaignId}/")
    public ResponseEntity<List<IprDTO>> findAllIprByCampaignId(@PathVariable Long campaignId) {
        List<IprDTO> result =
                ((IprService) service).findAllIprByCampaignId(campaignId);
        return ResponseEntity.ok(result);
    }

    @Override
    protected Optional<?> performFindById(Long id) {
        return Optional.of(
                ((IprService) service).findIprDTOById(id)
        );
    }

    @Override
    public IprDTO performUpdate(Long id, IprDTO payload) {
        return  ((IprService) service).updateIpr(id, payload);
    }

}
