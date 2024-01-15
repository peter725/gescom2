package es.consumo.gescom.modules.authorityOEU.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.authorityOEU.model.dto.AuthorityOEUDTO;
import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthorityOEUConverter extends SimpleDataConverter<AuthorityOEUEntity, AuthorityOEUDTO> {
    public AuthorityOEUConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
