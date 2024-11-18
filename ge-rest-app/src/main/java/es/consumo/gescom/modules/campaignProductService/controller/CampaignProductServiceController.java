package es.consumo.gescom.modules.campaignProductService.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaignProductService.model.criteria.CampaignProductServiceCriteria;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.service.CampaignProductServiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaignProductService")
@Tag(name = "Campaign Product Service controller")
public class CampaignProductServiceController extends AbstractCrudController<CampaignProductServiceEntity, CampaignProductServiceDTO, Long, FilterCriteria> {

    @Autowired
    public CampaignProductServiceController(CampaignProductServiceService service, DataConverter<CampaignProductServiceEntity, CampaignProductServiceDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<CampaignProductServiceEntity.SimpleProjection>> findListByCriteria(CampaignProductServiceCriteria servicesCriteria, @PathVariable  Long id) {
        Page<CampaignProductServiceEntity.SimpleProjection> result =
                ((CampaignProductServiceService) service).findAllCampaignProductById(new CriteriaWrapper<>(servicesCriteria), id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<List<CampaignProductServiceDTO>> findListByCriteria(@PathVariable  Long id) {
        List<CampaignProductServiceDTO> result =
                ((CampaignProductServiceService) service).findCampaignProductServiceByCampaignId(id);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/saveRelation")
    public ResponseEntity<List<CampaignProductServiceEntity>> saveRelation(@RequestBody List<CampaignProductServiceDTO> productos){
        List<CampaignProductServiceEntity> result = ((CampaignProductServiceService) service).saveRelation(productos);
        return ResponseEntity.ok(result);
    }

    @Override
    protected CampaignProductServiceDTO performCreate(CampaignProductServiceDTO payload) {
        return super.performCreate(payload);
    }

    @DeleteMapping("/delete/deleteRelation/{id}")
    public ResponseEntity<CampaignProductServiceEntity> deleteRelation(@PathVariable Long id) {
    	CampaignProductServiceEntity result = ((CampaignProductServiceService)service).deleteByIdCPSE(id);

        return ResponseEntity.ok(result);
    }

}
