package es.consumo.junta_arbitral.modules.module.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.modules.module.model.criteria.ModuleCriteria;
import es.consumo.junta_arbitral.modules.module.model.dto.ModuleDTO;
import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;
import es.consumo.junta_arbitral.modules.module.service.ModuleService;
import es.consumo.junta_arbitral.modules.permission.model.criteria.PermissionCriteria;
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
