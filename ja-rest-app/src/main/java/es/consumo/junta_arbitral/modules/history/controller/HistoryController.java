package es.consumo.junta_arbitral.modules.history.controller;

import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.modules.history.model.criteria.HistoryCriteria;
import es.consumo.junta_arbitral.modules.history.model.dto.HistoryDTO;
import es.consumo.junta_arbitral.modules.history.model.entity.HistoryEntity;
import es.consumo.junta_arbitral.modules.history.service.HistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/history")
@Tag(name = "History controller")
public class HistoryController extends AbstractCrudController<HistoryEntity, HistoryDTO, Long, FilterCriteria> {

    @Autowired
    public HistoryController(HistoryService service,
                            DataConverter<HistoryEntity, HistoryDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<HistoryEntity.SimpleProjection>> findListByCriteria(HistoryCriteria historyCriteria, @PathVariable  Long id) {
        Page<HistoryEntity.SimpleProjection> result =
                ((HistoryService) service).findAllHistoryByArbitration(new CriteriaWrapper<>(historyCriteria), id);
        return ResponseEntity.ok(result);
    }
}
