package es.dgc.gesco.model.modules.autonomousCommunity.converter;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutonomousComunityConverter {


    AutonomousCommunity convertDtoToAutonomousCommunity(AutonomousComunityDTO autonomousCommunityDTO);

    AutonomousComunityDTO convertAutonomousCommunityToDto(AutonomousCommunity autonomousCommunity);


}
