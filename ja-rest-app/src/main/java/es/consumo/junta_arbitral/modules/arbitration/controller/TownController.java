package es.consumo.junta_arbitral.modules.arbitration.controller;

import es.consumo.junta_arbitral.modules.general.model.criteria.TownCriteria;
import es.consumo.junta_arbitral.modules.general.model.entity.TownEntity;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractReadOnlyController;
import es.consumo.junta_arbitral.commons.db.repository.ReadOnlyRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/town")
@Tag(name = "Town controller")
public class TownController extends AbstractReadOnlyController<TownEntity, Long, TownCriteria> {
    @Autowired
    public TownController(ReadOnlyRepository<TownEntity, Long> repository) {
        super(repository);
    }


}
