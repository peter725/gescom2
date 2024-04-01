package es.consumo.gescom.modules.country.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.country.model.dto.CountryDTO;
import es.consumo.gescom.modules.country.model.entity.CountryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CountryConverter extends SimpleDataConverter<CountryEntity, CountryDTO> {
    public CountryConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
