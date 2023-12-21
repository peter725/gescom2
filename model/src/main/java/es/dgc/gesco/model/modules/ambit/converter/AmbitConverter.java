package es.dgc.gesco.model.modules.ambit.converter;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.ambit.dto.AmbitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AmbitConverter {
    @Mapping(target = "id", source = "id")
    AmbitDto convertAmbitToDto(Ambit ambit);
}