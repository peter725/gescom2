package es.consumo.gescom.modules.general.controller;

import es.consumo.gescom.modules.general.model.entity.ProvinceEntity;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractReadOnlyController;
import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
import es.consumo.gescom.commons.dto.FilterCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/province")
@Tag(name = "Province controller")
public class ProvinceController extends AbstractReadOnlyController<ProvinceEntity, Long, FilterCriteria> {
    @Autowired
    public ProvinceController(ReadOnlyRepository<ProvinceEntity, Long> repository) {
        super(repository);
    }
}
