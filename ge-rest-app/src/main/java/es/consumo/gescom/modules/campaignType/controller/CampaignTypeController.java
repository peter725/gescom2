package es.consumo.gescom.modules.campaignType.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaignType.model.criteria.CampaignTypeCriteria;
import es.consumo.gescom.modules.campaignType.model.dto.CampaignTypeDTO;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.campaignType.service.CampaignTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign_type")
@Tag(name = "Campaign Type Controller")
public class CampaignTypeController extends AbstractCrudController<CampaignTypeEntity, CampaignTypeDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignTypeController(CampaignTypeService service, DataConverter<CampaignTypeEntity, CampaignTypeDTO> campaingTypeDto) {
        super(service, campaingTypeDto);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<CampaignTypeEntity.SimpleProjection>> findListByCriteria(CampaignTypeCriteria campaignTypeCriteria, @PathVariable Long id) {
        Page<CampaignTypeEntity.SimpleProjection> result =
                ((CampaignTypeService) service).findAllCampaignTypeEntityById(new CriteriaWrapper<>(campaignTypeCriteria), id);
        return ResponseEntity.ok(result);
    }
}