package by.aston.jdbc.service.imp;

import by.aston.jdbc.dto.UserReq;
import by.aston.jdbc.dto.UserResp;
import by.aston.jdbc.entity.User;
import by.aston.jdbc.mapper.UserMapper;
import by.aston.jdbc.repository.UserDao;
import by.aston.jdbc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

}
