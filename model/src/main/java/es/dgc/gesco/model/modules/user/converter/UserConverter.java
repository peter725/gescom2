package es.dgc.gesco.model.modules.user.converter;

import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    final public UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "autonomousCommunity", source = "autonomousCommunity")
    @Mapping(target = "profile", source = "profile")
    @Mapping(target = "userType", source = "userType")
    User convertDtoToUser(UserDto userDto);


    UserDto convertUserToDto(User user);
}
