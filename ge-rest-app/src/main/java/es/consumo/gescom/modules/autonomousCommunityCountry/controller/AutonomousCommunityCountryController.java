package es.consumo.gescom.modules.autonomousCommunityCountry.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.criteria.AutonomousCommunityCountryCriteria;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.dto.AutonomousCommunityCountryDTO;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.entity.AutonomousCommunityCountryEntity;
import es.consumo.gescom.modules.autonomousCommunityCountry.service.AutonomousCommunityCountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/autonomous_community_country")
@Tag(name = "Autonomous Community Country controller")
public class AutonomousCommunityCountryController extends AbstractCrudController<AutonomousCommunityCountryEntity, AutonomousCommunityCountryDTO, Long, FilterCriteria> {

    @Autowired
    public AutonomousCommunityCountryController(AutonomousCommunityCountryService service,
                                                       DataConverter<AutonomousCommunityCountryEntity, AutonomousCommunityCountryDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<AutonomousCommunityCountryEntity.SimpleProjection>> findListByCriteria(AutonomousCommunityCountryCriteria autonomousCommunityCriteria, @PathVariable  Long id) {
        Page<AutonomousCommunityCountryEntity.SimpleProjection> result =
                ((AutonomousCommunityCountryService) service).findAllAutonomousCommunityCountryByName(new CriteriaWrapper<>(autonomousCommunityCriteria), id);
        return ResponseEntity.ok(result);
    }
}
