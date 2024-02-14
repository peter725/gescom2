package es.consumo.gescom.modules.campaignProduct.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaignProduct.model.criteria.CampaignProductCriteria;
import es.consumo.gescom.modules.campaignProduct.model.dto.CampaignProductDTO;
import es.consumo.gescom.modules.campaignProduct.model.entity.CampaignProductEntity;
import es.consumo.gescom.modules.campaignProduct.service.CampaignProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaignProduct")
@Tag(name = "Campaign Product controller")
public class CampaignProductController extends AbstractCrudController<CampaignProductEntity, CampaignProductDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignProductController(CampaignProductService service, DataConverter<CampaignProductEntity, CampaignProductDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<CampaignProductEntity.SimpleProjection>> findListByCriteria(CampaignProductCriteria servicesCriteria, @PathVariable  Long id) {
        Page<CampaignProductEntity.SimpleProjection> result =
                ((CampaignProductService) service).findAllCampaignProductById(new CriteriaWrapper<>(servicesCriteria), id);
        return ResponseEntity.ok(result);
    }
}
