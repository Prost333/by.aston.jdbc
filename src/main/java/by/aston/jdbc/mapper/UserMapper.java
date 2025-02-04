package by.aston.jdbc.mapper;

import by.aston.jdbc.dto.UserReq;
import by.aston.jdbc.dto.UserResp;
import by.aston.jdbc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserResp toResponse(User user);
    @Mapping(target = "id", ignore = true)
    User toRequest(UserReq userReq);
}
