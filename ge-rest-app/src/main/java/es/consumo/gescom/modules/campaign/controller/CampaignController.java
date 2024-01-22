package es.consumo.gescom.modules.campaign.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign")
@Tag(name = "Campaign controller")
public class CampaignController extends AbstractCrudController<CampaignEntity, CampaignDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignController(CampaignService service, DataConverter<CampaignEntity, CampaignDTO> dataConverter) {
        super(service, dataConverter);
    }

    @PostMapping("/create")
    public ResponseEntity<CampaignEntity> createCampaign(@RequestBody CampaignDTO campaignDTO) {
        CampaignEntity result = ((CampaignService) service).createCampaign(campaignDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateCampaign/{id}")
    public ResponseEntity<CampaignEntity> updateCampaign(@RequestBody CampaignDTO campaignDTO) {
        CampaignEntity result = ((CampaignService) service).updateCampaign(campaignDTO);
        return ResponseEntity.ok(result);
    }

//    @GetMapping ("/findCampaign/{id}")
//    public ResponseEntity<CampaignDTO> findCampaignById(@RequestBody Long idCampaignDTO) {
//        CampaignDTO result = ((CampaignService) service).findCampaignById(idCampaignDTO);
//        return ResponseEntity.ok(result);
//    }

}
