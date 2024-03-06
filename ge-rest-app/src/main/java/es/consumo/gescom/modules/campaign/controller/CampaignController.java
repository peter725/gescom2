package es.consumo.gescom.modules.campaign.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.dto.ChangePhaseDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign")
@Tag(name = "Campaign controller")
public class CampaignController extends AbstractCrudController<CampaignEntity, CampaignDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignController(CampaignService service, DataConverter<CampaignEntity, CampaignDTO> dataConverter) {
        super(service, dataConverter);
    }

    @Override
    protected CampaignDTO performCreate(CampaignDTO payload) {
        return ((CampaignService) service).createCampaign(payload);
    }

    @Override
    protected CampaignDTO performUpdate(Long id, CampaignDTO payload) {
        return  ((CampaignService) service).updateCampaign(id, payload);
    }

    @Override
    protected Optional<?> performFindById(Long id) {

        return Optional.of(
                ((CampaignService) service).findCampaignById(id)
        );
    }


    @Override
    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        return ((CampaignService) service).performFindAllCampaing(criteriaWrapper);
    }

    @PostMapping("/{id}/switch")
    public ResponseEntity<CampaignEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        CampaignEntity result = ((CampaignService) service).switchStatus(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/phase")
    public ResponseEntity<CampaignEntity> switchPhase(@RequestBody PhaseDTO changeStatus, @PathVariable  Long id) {
        CampaignEntity result = ((CampaignService) service).switchPhase(changeStatus, id);
        return ResponseEntity.ok(result);
    }

}
