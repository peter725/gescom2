package es.consumo.gescom.modules.authorityOEU.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.authorityDGC.model.criteria.AuthorityDGCCriteria;
import es.consumo.gescom.modules.authorityDGC.model.dto.AuthorityDGCDTO;
import es.consumo.gescom.modules.authorityDGC.model.entity.AuthorityDGCEntity;
import es.consumo.gescom.modules.authorityDGC.service.AuthorityDGCService;
import es.consumo.gescom.modules.authorityOEU.model.criteria.AuthorityOEUCriteria;
import es.consumo.gescom.modules.authorityOEU.model.dto.AuthorityOEUDTO;
import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import es.consumo.gescom.modules.authorityOEU.service.AuthorityOEUService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/authority_oeu")
@Tag(name = "Authority_OEU controller")
public class AuthorityOEUController extends AbstractCrudController<AuthorityOEUEntity, AuthorityOEUDTO, Long, FilterCriteria> {

    @Autowired
    public AuthorityOEUController(AuthorityOEUService service,
                                  DataConverter<AuthorityOEUEntity, AuthorityOEUDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{name}")
    public ResponseEntity<Page<AuthorityOEUEntity.SimpleProjection>> findListByCriteria(AuthorityOEUCriteria authorityOEUCriteria, @PathVariable  String name) {
        Page<AuthorityOEUEntity.SimpleProjection> result =
                ((AuthorityOEUService) service).findAllAuthorityOEUByName(new CriteriaWrapper<>(authorityOEUCriteria), name);
        return ResponseEntity.ok(result);
    }
}
