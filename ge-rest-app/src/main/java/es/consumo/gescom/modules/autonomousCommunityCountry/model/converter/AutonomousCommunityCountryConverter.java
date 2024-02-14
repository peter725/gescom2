package es.consumo.gescom.modules.autonomousCommunityCountry.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.dto.AutonomousCommunityCountryDTO;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.entity.AutonomousCommunityCountryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AutonomousCommunityCountryConverter extends SimpleDataConverter<AutonomousCommunityCountryEntity, AutonomousCommunityCountryDTO> {
    public AutonomousCommunityCountryConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
