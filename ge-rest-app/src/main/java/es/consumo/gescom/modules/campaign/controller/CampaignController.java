package es.consumo.gescom.modules.campaign.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign")
@Tag(name = "Campaign controller")
public class CampaignController extends AbstractCrudController<CampaignEntity, CampaignDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignController(CampaignService service, DataConverter<CampaignEntity, CampaignDTO> dataConverter) {
        super(service, dataConverter);
    }

}
