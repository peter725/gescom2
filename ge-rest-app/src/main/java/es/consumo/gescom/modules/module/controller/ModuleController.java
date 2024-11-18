package es.consumo.gescom.modules.module.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.module.model.criteria.ModuleCriteria;
import es.consumo.gescom.modules.module.model.dto.ModuleDTO;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.module.service.ModuleService;
import es.consumo.gescom.modules.permission.model.criteria.PermissionCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/module")
@Tag(name = "Module controller")
public class ModuleController extends AbstractCrudController<ModuleEntity, ModuleDTO, Long, PermissionCriteria> {

    protected ModuleController(CrudService<ModuleEntity, Long> service,
            DataConverter<ModuleEntity, ModuleDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ModuleEntity.SimpleProjection>> findListByCriteria(ModuleCriteria moduleCriteria) {

        Page<ModuleEntity.SimpleProjection> result =
                ((ModuleService) service).findAllSimpleEntity(new CriteriaWrapper<>(moduleCriteria));
        return ResponseEntity.ok(result);
    }


}
