package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.db.entity.Language;
import es.consumo.gescom.commons.dto.GeneralCriteria;
import es.consumo.gescom.commons.service.CrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author serikat
 */
@RestController
@RequestMapping(ApiEndpoints.V1_API + "/languages")
@Tag(name = "Language management")
public class LanguageController extends EntityCrudController<Language, Long, GeneralCriteria> {
    public LanguageController(CrudService<Language, Long> service) {
        super(service);
    }
}
