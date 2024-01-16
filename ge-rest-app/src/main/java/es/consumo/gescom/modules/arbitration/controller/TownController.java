package es.consumo.gescom.modules.arbitration.controller;

import es.consumo.gescom.modules.general.model.criteria.TownCriteria;
import es.consumo.gescom.modules.general.model.entity.TownEntity;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractReadOnlyController;
import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
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
