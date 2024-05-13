package by.aston.jdbc.service.imp;

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
    private UserDao userDao;
    private UserMapper userMapper;

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        userDao.deleteUser(id);
    }

    @Override
    public UserResp findById(Long id) {
        return userMapper.toResponse(userDao.findById(id));
    }

    @Override
    public List<UserResp> findAll() {
        return userDao.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String findUserNameById(Long id) {
        return userDao.findUserNameById(id);
    }
}
