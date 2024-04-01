package es.consumo.gescom.modules.totalProtocolResults.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TotalProtocolResultsConverter extends SimpleDataConverter<TotalProtocolResultsEntity, TotalProtocolResultsDTO> {
    public TotalProtocolResultsConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
