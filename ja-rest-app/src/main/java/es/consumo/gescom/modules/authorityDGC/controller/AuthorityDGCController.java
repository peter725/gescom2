package es.consumo.gescom.modules.authorityDGC.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.authorityDGC.model.criteria.AuthorityDGCCriteria;
import es.consumo.gescom.modules.authorityDGC.model.dto.AuthorityDGCDTO;
import es.consumo.gescom.modules.authorityDGC.model.entity.AuthorityDGCEntity;
import es.consumo.gescom.modules.authorityDGC.service.AuthorityDGCService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/authority_dgc")
@Tag(name = "Authority_DGC controller")
public class AuthorityDGCController extends AbstractCrudController<AuthorityDGCEntity, AuthorityDGCDTO, Long, FilterCriteria> {

    @Autowired
    public AuthorityDGCController(AuthorityDGCService service,
                                  DataConverter<AuthorityDGCEntity, AuthorityDGCDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{name}")
    public ResponseEntity<Page<AuthorityDGCEntity.SimpleProjection>> findListByCriteria(AuthorityDGCCriteria authorityDGCCriteria, @PathVariable  String name) {
        Page<AuthorityDGCEntity.SimpleProjection> result =
                ((AuthorityDGCService) service).findAllAuthorityDGCByName(new CriteriaWrapper<>(authorityDGCCriteria), name);
        return ResponseEntity.ok(result);
    }
}
