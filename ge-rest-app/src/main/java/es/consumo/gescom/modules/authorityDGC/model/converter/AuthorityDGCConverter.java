package es.consumo.gescom.modules.authorityDGC.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.authorityDGC.model.dto.AuthorityDGCDTO;
import es.consumo.gescom.modules.authorityDGC.model.entity.AuthorityDGCEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthorityDGCConverter extends SimpleDataConverter<AuthorityDGCEntity, AuthorityDGCDTO> {
    public AuthorityDGCConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
