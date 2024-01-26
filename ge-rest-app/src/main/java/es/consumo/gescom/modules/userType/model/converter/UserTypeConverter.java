package es.consumo.gescom.modules.userType.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.userType.model.dto.UserTypeDTO;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserTypeConverter extends SimpleDataConverter<UserTypeEntity, UserTypeDTO> {
    public UserTypeConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
