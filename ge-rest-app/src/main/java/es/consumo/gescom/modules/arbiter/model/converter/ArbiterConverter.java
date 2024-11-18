package es.consumo.gescom.modules.arbiter.model.converter;

import es.consumo.gescom.modules.arbiter.model.dto.ArbiterDTO;
import es.consumo.gescom.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.gescom.commons.converter.SimpleDataConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArbiterConverter extends SimpleDataConverter<ArbiterEntity, ArbiterDTO> {
    public ArbiterConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
