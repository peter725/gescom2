package es.consumo.gescom.modules.infringement.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.infringement.model.dto.InfringementDTO;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class InfringementConverter extends SimpleDataConverter<InfringementEntity, InfringementDTO> {

    public InfringementConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
