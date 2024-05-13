package by.aston.jdbc.controller;

import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.service.imp.DriveServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class DriveController {
    @Autowired
    private DriveServiceImp driveService;

    @PostMapping
    public void saveDrive(@RequestBody Drive drive) throws SQLException {
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