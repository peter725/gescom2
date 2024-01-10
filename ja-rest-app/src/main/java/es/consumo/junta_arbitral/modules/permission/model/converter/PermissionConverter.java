package es.consumo.junta_arbitral.modules.permission.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import es.consumo.junta_arbitral.modules.permission.model.dto.PermissionDTO;
import es.consumo.junta_arbitral.modules.permission.model.entity.PermissionEntity;

@Service
public class PermissionConverter extends SimpleDataConverter<PermissionEntity, PermissionDTO> {
    public PermissionConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
