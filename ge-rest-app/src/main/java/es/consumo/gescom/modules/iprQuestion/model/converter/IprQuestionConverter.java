package es.consumo.gescom.modules.iprQuestion.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class IprQuestionConverter extends SimpleDataConverter<IprQuestionEntity, IprQuestionDTO> {
    public IprQuestionConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
