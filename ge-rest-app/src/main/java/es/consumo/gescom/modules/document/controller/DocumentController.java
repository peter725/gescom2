package es.consumo.gescom.modules.document.controller;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.dto.DocumentDTO;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.document.service.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/document")
@Tag(name = "Document controller")
public class DocumentController extends AbstractCrudController<DocumentEntity, DocumentDTO, Long, DocumentCriteria> {

    @Autowired
    public DocumentController(CrudService<DocumentEntity, Long> service,
                              DataConverter<DocumentEntity, DocumentDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<Page<DocumentEntity>> findListByCriteria(DocumentCriteria protocolCriteria, @PathVariable  Long id) {
        Page<DocumentEntity> result =
                ((DocumentService) service).findDocumentByCampaignId(new CriteriaWrapper<>(protocolCriteria), id);
        return ResponseEntity.ok(result);    }
}
