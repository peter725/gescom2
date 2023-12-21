package es.dgc.gesco.model.specialist.converter;

import es.dgc.gesco.model.specialist.db.entity.Specialist;
import es.dgc.gesco.model.specialist.dto.SpecialistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialistConverter {
    @Mapping(target = "id", source = "id")
    SpecialistDto convertSpecialistToDto(Specialist specialist);
}