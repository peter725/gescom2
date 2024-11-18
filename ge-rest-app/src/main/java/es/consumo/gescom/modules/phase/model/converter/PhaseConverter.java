package es.consumo.gescom.modules.phase.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PhaseConverter extends SimpleDataConverter<PhaseEntity, PhaseDTO> {
    public PhaseConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
