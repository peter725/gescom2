package es.consumo.junta_arbitral.modules.users.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import es.consumo.junta_arbitral.modules.users.model.dto.UserDTO;
import es.consumo.junta_arbitral.modules.users.model.entity.UserEntity;
@Service
public class UserConverter extends SimpleDataConverter<UserEntity, UserDTO> {
    public UserConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
