package es.consumo.gescom.modules.proponent.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProponentConverter extends SimpleDataConverter<ProponentEntity, ProponentDTO> {
    public ProponentConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
