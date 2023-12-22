package es.dgc.gesco.model.modules.proponent.converter;

import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProponentConverter {
    @Mapping(target = "id", source = "id")
    ProponentDTO convertProponentToDto(Proponent proponent);

    Proponent convertProponentDtoToEntity(ProponentDTO proponentDto);
}