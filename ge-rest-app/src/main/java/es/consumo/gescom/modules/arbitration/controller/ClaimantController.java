package es.consumo.gescom.modules.arbitration.controller;

import es.consumo.gescom.modules.arbitration.model.entity.ClaimantEntity;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractReadOnlyController;
import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
import es.consumo.gescom.commons.dto.FilterCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/claimant")
@Tag(name = "Claimant controller")
public class ClaimantController extends AbstractReadOnlyController<ClaimantEntity, Long, FilterCriteria> {
    @Autowired
    public ClaimantController(ReadOnlyRepository<ClaimantEntity, Long> repository) {
        super(repository);
    }
}
