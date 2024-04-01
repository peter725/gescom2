package es.consumo.gescom.modules.protocol_results.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProtocolResultsConverter extends SimpleDataConverter<ProtocolResultsEntity, ProtocolResultsDTO> {
    public ProtocolResultsConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
