package es.consumo.gescom.modules.autonomousCommunity.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.campaignProposal.service.CampaignProposalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/autonomous_community")
@Tag(name = "Autonomous_Community controller")
public class AutonomousCommunityController extends AbstractCrudController<AutonomousCommunityEntity, AutonomousCommunityDTO, Long, AutonomousCommunityCriteria> {

    @Autowired
    public AutonomousCommunityController(AutonomousCommunityService service,
                                         DataConverter<AutonomousCommunityEntity, AutonomousCommunityDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{name}")
    public ResponseEntity<Page<AutonomousCommunityEntity.SimpleProjection>> findListByCriteria(AutonomousCommunityCriteria autonomousCommunityCriteria, @PathVariable  String name) {
        Page<AutonomousCommunityEntity.SimpleProjection> result =
                ((AutonomousCommunityService) service).findAllAutonomousCommunityByName(new CriteriaWrapper<>(autonomousCommunityCriteria), name);
        return ResponseEntity.ok(result);
    }


    @Override
    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        return ((AutonomousCommunityService) service).findAllCCAAActive(criteriaWrapper);
    }

}
