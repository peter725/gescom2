package es.consumo.junta_arbitral.modules.general.controller;

import es.consumo.junta_arbitral.modules.general.model.entity.RoadTypeEntity;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractReadOnlyController;
import es.consumo.junta_arbitral.commons.db.repository.ReadOnlyRepository;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/road-type")
@Tag(name = "Road Type controller")
public class RoadTypeController extends AbstractReadOnlyController<RoadTypeEntity, Long, FilterCriteria> {
    @Autowired
    public RoadTypeController(ReadOnlyRepository<RoadTypeEntity, Long> repository) {
        super(repository);
    }
}
