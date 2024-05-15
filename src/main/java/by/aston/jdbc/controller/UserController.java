package by.aston.jdbc.controller;

import by.aston.jdbc.dto.UserReq;
import by.aston.jdbc.dto.UserResp;
import by.aston.jdbc.entity.User;
import by.aston.jdbc.service.imp.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private  UserServiceImp userService;

    @PostMapping
    public void saveUser(@RequestBody UserReq user) throws SQLException {
        userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws SQLException {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserResp getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserResp> getAllUsers() {
        return userService.findAll();
    }
}
