package es.consumo.gescom.modules.arbitrationBoard.model.converter;

import es.consumo.gescom.modules.arbitrationBoard.model.dto.ArbitrationBoardDTO;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.commons.converter.SimpleDataConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArbitrationBoardConverter extends SimpleDataConverter<ArbitrationBoardEntity, ArbitrationBoardDTO> {

    public ArbitrationBoardConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
