package es.consumo.junta_arbitral.modules.arbiter.model.converter;

import es.consumo.junta_arbitral.modules.arbiter.model.dto.ArbiterDTO;
import es.consumo.junta_arbitral.modules.arbiter.model.entity.ArbiterEntity;
import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArbiterConverter extends SimpleDataConverter<ArbiterEntity, ArbiterDTO> {
    public ArbiterConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
