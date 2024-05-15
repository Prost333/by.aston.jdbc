package by.aston.jdbc.controller;

import by.aston.jdbc.dto.DriveReq;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.service.imp.DriveServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/drives")
@AllArgsConstructor
public class DriveController {


}