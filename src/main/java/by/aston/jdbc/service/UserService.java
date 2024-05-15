package by.aston.jdbc.service;

import by.aston.jdbc.dto.UserReq;
import by.aston.jdbc.dto.UserResp;
import by.aston.jdbc.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void addUser(UserReq user) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    UserResp findById(Long id);

    List<UserResp> findAll();
    String findUserNameById(Long id);
}
