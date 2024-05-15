package by.aston.jdbc.controller;

import by.aston.jdbc.dto.RatesReq;
import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.Rates;
import by.aston.jdbc.service.imp.RatesServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/rates")
@AllArgsConstructor
public class RatesController {


}