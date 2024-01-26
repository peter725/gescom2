package es.consumo.gescom.modules.profile.model.converter;

import es.consumo.gescom.modules.profile.model.dto.ProfileDTO;
import es.consumo.gescom.modules.profile.model.entity.ProfileEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.permission.model.dto.PermissionDTO;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;

@Service
public class ProfileConverter extends SimpleDataConverter<ProfileEntity, ProfileDTO> {
    public ProfileConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
