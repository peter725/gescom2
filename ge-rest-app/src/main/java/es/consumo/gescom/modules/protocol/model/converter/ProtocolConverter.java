package es.consumo.gescom.modules.protocol.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProtocolConverter extends SimpleDataConverter<ProtocolEntity, ProtocolDTO> {
    public ProtocolConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
