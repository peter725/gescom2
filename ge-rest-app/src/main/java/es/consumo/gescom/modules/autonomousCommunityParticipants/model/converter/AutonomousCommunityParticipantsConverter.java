package es.consumo.gescom.modules.autonomousCommunityParticipants.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.dto.AutonomousCommunityParticipantsDTO;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AutonomousCommunityParticipantsConverter extends SimpleDataConverter<AutonomousCommunityParticipantsEntity, AutonomousCommunityParticipantsDTO> {

    public AutonomousCommunityParticipantsConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
