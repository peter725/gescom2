package es.dgc.gesco.model.modules.approach.converter;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.approach.dto.ApproachDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApproachConverter {

    final public ApproachConverter INSTANCE = Mappers.getMapper(ApproachConverter.class);

    Approach convertDtoToUsuer(ApproachDTO approachDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "autonomousCommunityId", source = "autonomousCommunityId")
    @Mapping(target = "state", source = "state")
    ApproachDTO convertApproachToDto(Approach approach);
}