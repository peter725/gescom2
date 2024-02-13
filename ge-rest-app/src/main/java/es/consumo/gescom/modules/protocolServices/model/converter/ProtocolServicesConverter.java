package es.consumo.gescom.modules.protocolServices.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.protocolServices.model.dto.ProtocolServicesDTO;
import es.consumo.gescom.modules.protocolServices.model.entity.ProtocolServicesEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProtocolServicesConverter extends SimpleDataConverter<ProtocolServicesEntity, ProtocolServicesDTO> {
    public ProtocolServicesConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
