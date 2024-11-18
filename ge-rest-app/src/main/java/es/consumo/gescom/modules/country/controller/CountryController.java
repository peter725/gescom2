package es.consumo.gescom.modules.country.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.country.model.criteria.CountryCriteria;
import es.consumo.gescom.modules.country.model.dto.CountryDTO;
import es.consumo.gescom.modules.country.model.entity.CountryEntity;
import es.consumo.gescom.modules.country.service.CountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/country")
@Tag(name = "Country controller")
public class CountryController extends AbstractCrudController<CountryEntity, CountryDTO, Long, FilterCriteria> {

    @Autowired
    public CountryController(CountryService service, DataConverter<CountryEntity, CountryDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<CountryEntity.SimpleProjection>> findListByCriteria(CountryCriteria countryCriteria, @PathVariable  Long id) {
        Page<CountryEntity.SimpleProjection> result =
                ((CountryService) service).findAllCountryById(new CriteriaWrapper<>(countryCriteria), id);
        return ResponseEntity.ok(result);
    }
}
