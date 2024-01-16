package es.consumo.gescom.modules.autonomousCommunity.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AutonomousCommunityConverter extends SimpleDataConverter<AutonomousCommunityEntity, AutonomousCommunityDTO> {
    public AutonomousCommunityConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
