package by.aston.jdbc.mapper;

import by.aston.jdbc.dto.DriveReq;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.City;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.entity.User;
import by.aston.jdbc.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface DriveMapper {

    @Mapping(source = "user.name", target = "userName")
    DriveResp toResponse(Drive drive);

    @Mapping(target = "id", ignore = true)
    Drive toRequest(DriveReq driveReq);
    default String map(User user) {
        return user.getName();
    }

    default User map(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

}
