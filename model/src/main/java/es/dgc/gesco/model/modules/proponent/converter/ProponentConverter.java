package es.dgc.gesco.model.modules.proponent.converter;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.ambit.dto.AmbitDto;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProponentConverter {
    @Mapping(target = "id", source = "id")
    ProponentDto convertProponentToDto(Proponent proponent);
}