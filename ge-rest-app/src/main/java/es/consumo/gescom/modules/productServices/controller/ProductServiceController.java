package es.consumo.gescom.modules.productServices.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.productServices.model.criteria.ProductServiceCriteria;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.productServices.service.ProductServiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/productServices")
@Tag(name = "Product Services controller")
public class ProductServiceController extends AbstractCrudController<ProductServiceEntity, ProductServiceDTO, Long, ProductServiceCriteria> {

    @Autowired
    public ProductServiceController(ProductServiceService service, DataConverter<ProductServiceEntity, ProductServiceDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<ProductServiceEntity>> findListByCriteria(ProductServiceCriteria servicesCriteria, @PathVariable  Long id) {
        Page<ProductServiceEntity> result =
                ((ProductServiceService) service).findAllProductServiceById(new CriteriaWrapper<>(servicesCriteria), id);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Object> findAll(@Valid ProductServiceCriteria criteria) {

        return super.findAll(criteria);
    }
}
