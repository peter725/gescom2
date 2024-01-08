package es.dgc.gesco.model.modules.protocol.converter;

import es.dgc.gesco.model.modules.protocol.db.entity.Protocol;
import es.dgc.gesco.model.modules.protocol.dto.ProtocolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProtocolConverter {

    @Mapping(target = "id", source = "id")
    ProtocolDTO convertProtocolToDto(Protocol protocol);

    @Mapping(target = "id", source = "id")
    Protocol convertDtoToProtocol(ProtocolDTO protocolDTO);
}
