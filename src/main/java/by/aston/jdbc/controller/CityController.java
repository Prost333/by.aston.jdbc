package by.aston.jdbc.controller;

import by.aston.jdbc.entity.City;
import by.aston.jdbc.service.imp.CityServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

}
