package es.consumo.gescom.modules.sumProtocol.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.sumProtocol.model.dto.SumProtocolDTO;
import es.consumo.gescom.modules.sumProtocol.model.entity.SumProtocolEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SumProtocolConverter extends SimpleDataConverter<SumProtocolEntity, SumProtocolDTO> {
    public SumProtocolConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
