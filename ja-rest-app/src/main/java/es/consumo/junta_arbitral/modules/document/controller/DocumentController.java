package es.consumo.junta_arbitral.modules.document.controller;

import es.consumo.junta_arbitral.modules.document.model.criteria.DocumentCriteria;
import es.consumo.junta_arbitral.modules.document.model.dto.DocumentDTO;
import es.consumo.junta_arbitral.modules.document.model.entity.DocumentEntity;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import es.consumo.junta_arbitral.commons.controller.AbstractCrudController;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.service.CrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/document")
@Tag(name = "Document controller")
public class DocumentController extends AbstractCrudController<DocumentEntity, DocumentDTO, Long, DocumentCriteria> {

//    private final ObjectFactory<TulsaSampleFileLoader> sampleFileLoader;

    @Autowired
    public DocumentController(CrudService<DocumentEntity, Long> service,
                              DataConverter<DocumentEntity, DocumentDTO> dataConverter) {
        super(service, dataConverter);
    }


}
