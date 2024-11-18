package es.consumo.gescom.modules.specialist.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SpecialistConverter extends SimpleDataConverter<SpecialistEntity, SpecialistDTO> {
    public SpecialistConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
