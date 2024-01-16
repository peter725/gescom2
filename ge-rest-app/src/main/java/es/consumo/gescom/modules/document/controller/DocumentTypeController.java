package es.consumo.gescom.modules.document.controller;

import es.consumo.gescom.modules.document.model.criteria.DocumentTypeCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentTypeEntity;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractReadController;
import es.consumo.gescom.commons.service.EntityReadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/document-type")
@Tag(name = "Document Type controller")
public class DocumentTypeController extends AbstractReadController<EntityReadService<DocumentTypeEntity,Long>,DocumentTypeEntity, Long, DocumentTypeCriteria> {

    @Autowired
    public DocumentTypeController(EntityReadService<DocumentTypeEntity, Long> service) {
        super(service);
    }
}
