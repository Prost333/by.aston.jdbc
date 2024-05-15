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

}
