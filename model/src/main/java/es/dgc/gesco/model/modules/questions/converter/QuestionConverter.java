package es.dgc.gesco.model.modules.questions.converter;

import es.dgc.gesco.model.modules.questions.db.entity.Questions;
import es.dgc.gesco.model.modules.questions.dto.QuestionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionConverter {

    @Mapping(target = "id", source = "id")
    QuestionDTO convertQuestionsToDto(Questions questions);

}