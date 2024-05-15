package by.aston.jdbc.controller;

import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.service.imp.NameRatesServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/namerates")
@AllArgsConstructor
public class NameRatesController {
}
