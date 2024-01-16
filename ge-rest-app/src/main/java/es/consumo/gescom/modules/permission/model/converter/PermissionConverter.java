package es.consumo.gescom.modules.permission.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;

@Service
public class PermissionConverter extends SimpleDataConverter<PermissionEntity, PermissionDTO> {
    public PermissionConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
