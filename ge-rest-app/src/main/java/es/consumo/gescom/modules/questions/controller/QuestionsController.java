package es.consumo.gescom.modules.questions.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.questions.model.criteria.QuestionsCriteria;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import es.consumo.gescom.modules.questions.service.QuestionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/questions")
@Tag(name = "Questions controller")
public class QuestionsController extends AbstractCrudController<QuestionsEntity, QuestionsDTO, Long, FilterCriteria> {

    @Autowired
    public QuestionsController(QuestionsService service, DataConverter<QuestionsEntity, QuestionsDTO> dataConverter) {
        super(service, dataConverter);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Page<QuestionsEntity.SimpleProjection>> findListByCriteria(QuestionsCriteria ambitCriteria, @PathVariable  Long id) {
        Page<QuestionsEntity.SimpleProjection> result =
                ((QuestionsService) service).findAllQuestionsById(new CriteriaWrapper<>(ambitCriteria), id);
        return ResponseEntity.ok(result);
    }
}
