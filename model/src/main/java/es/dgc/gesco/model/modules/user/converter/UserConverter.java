package es.dgc.gesco.model.modules.user.converter;

import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {


    @Mapping(target = "autonomousCommunity", source = "autonomousCommunity")
    @Mapping(target = "profile", source = "profile")
    @Mapping(target = "userType", source = "userType")
    User convertDtoToUser(UserDTO userDto);


    UserDTO convertUserToDto(User user);
}
