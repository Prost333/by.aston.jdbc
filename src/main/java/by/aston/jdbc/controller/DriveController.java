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

    private DriveServiceImp driveService;

    @PostMapping
    public void saveDrive(@RequestBody DriveReq drive) throws SQLException {
        driveService.save(drive);
    }

    @DeleteMapping("/{id}")
    public void deleteDrive(@PathVariable Long id) throws SQLException {
            driveService.delete(id);

    }

    @GetMapping("/{id}")
    public DriveResp getDrive(@PathVariable Long id) {
        return driveService.findById(id);
    }

    @GetMapping
    public List<DriveResp> getAllDrives() {
        return driveService.findAll();
    }
}