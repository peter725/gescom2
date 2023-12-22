package es.dgc.gesco.model.modules.specialist.converter;

import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialistConverter {
    @Mapping(target = "id", source = "id")
    SpecialistDTO convertSpecialistToDto(Specialist specialist);

    Specialist convertSpecialistDtoToEntity(SpecialistDTO specialistDto);
}