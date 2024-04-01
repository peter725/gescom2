package es.consumo.gescom.modules.ipr.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class IprConverter extends SimpleDataConverter<IprEntity, IprDTO> {
    public IprConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
