package by.aston.jdbc.mapper;

import by.aston.jdbc.dto.DriveReq;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserService.class)
public abstract class DriveMapper {

    @Autowired
    private UserService userService;

    @Mapping(target = "userName", ignore = true)
    public abstract DriveResp toResponse(Drive drive);

    @Mapping(target = "id", ignore = true)
    public abstract Drive toRequest(DriveReq driveReq);

}
