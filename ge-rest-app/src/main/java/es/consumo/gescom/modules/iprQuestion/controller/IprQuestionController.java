package es.consumo.gescom.modules.iprQuestion.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.iprQuestion.model.criteria.IprQuestionCriteria;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.service.IprQuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/iprQuestion")
@Tag(name = "IPR Question controller")
public class IprQuestionController extends AbstractCrudController<IprQuestionEntity, IprQuestionDTO, Long, FilterCriteria> {

    @Autowired
    public IprQuestionController(IprQuestionService service, DataConverter<IprQuestionEntity, IprQuestionDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/protocolo/{id}")
    public ResponseEntity<Page<IprQuestionEntity.SimpleProjection>> findListByCriteria(IprQuestionCriteria iprCriteria, @PathVariable  Long id) {
        Page<IprQuestionEntity.SimpleProjection> result =
                ((IprQuestionService) service).findAllIprQuestionByIdProtocol(new CriteriaWrapper<>(iprCriteria), id);
        return ResponseEntity.ok(result);
    }


}
