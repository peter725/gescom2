package es.consumo.gescom.modules.questions.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionsConverter extends SimpleDataConverter<QuestionsEntity, QuestionsDTO> {
    public QuestionsConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
