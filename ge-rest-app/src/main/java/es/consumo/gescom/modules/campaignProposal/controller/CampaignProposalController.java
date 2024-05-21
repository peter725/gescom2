package es.consumo.gescom.modules.campaignProposal.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.dto.CampaignProposalDTO;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import es.consumo.gescom.modules.campaignProposal.service.CampaignProposalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign_proposal")
@Tag(name = "Campaign Proposal controller")
public class CampaignProposalController extends AbstractCrudController<CampaignProposalEntity, CampaignProposalDTO, Long, CampaignProposalCriteria> {

    @Autowired
    public CampaignProposalController(CampaignProposalService service, DataConverter<CampaignProposalEntity, CampaignProposalDTO> dataConverter) {
        super(service, dataConverter);
    }


    @Override
    public Optional<?> performFindById(Long id) {
        return Optional.of(((CampaignProposalService) service).findCampaignProposalById(id));
    }

    @Override
    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        return ((CampaignProposalService) service).findAllCampaignProposal(criteriaWrapper);
    }

    @Override
    public CampaignProposalDTO performCreate(CampaignProposalDTO payload) {
        return ((CampaignProposalService) service).createCampaignProposal(payload);
    }

    @PostMapping("/{id}/switch")
    public ResponseEntity<CampaignProposalEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        CampaignProposalEntity result = ((CampaignProposalService) service).switchStatus(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<CampaignProposalEntity.SimpleProjection>> findListByCriteria(CampaignProposalCriteria campaignProposalCriteria, @PathVariable  Long id) {
        Page<CampaignProposalEntity.SimpleProjection> result = ((CampaignProposalService) service).findAllCampaignProposalById(new CriteriaWrapper<>(campaignProposalCriteria), id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findCampaignByYear/{year}")
    public ResponseEntity<Page<CampaignProposalEntity.SimpleProjection>> findListByCriteriaYear(CampaignProposalCriteria campaignProposalCriteria, @PathVariable  int year) {
        Page<CampaignProposalEntity.SimpleProjection> result = ((CampaignProposalService) service).findCampaignProposalByYear(new CriteriaWrapper<>(campaignProposalCriteria), year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findProposalByCAId/{idCA}")
    public ResponseEntity<Page<CampaignProposalEntity.SimpleProjection>> findListByCriteriaAutonomousCommunityId(CampaignProposalCriteria campaignProposalCriteria, @PathVariable  Long idCA) {
        Page<CampaignProposalEntity.SimpleProjection> result = ((CampaignProposalService) service).findListByCriteriaAutonomousCommunityId(new CriteriaWrapper<>(campaignProposalCriteria), idCA);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Object> update(Long id, @RequestBody CampaignProposalDTO payload) {
        CampaignProposalEntity result = ((CampaignProposalService) service).update(payload);
        return ResponseEntity.ok(result);
    }

}
