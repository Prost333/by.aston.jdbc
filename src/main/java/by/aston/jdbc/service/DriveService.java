package by.aston.jdbc.service;

import by.aston.jdbc.dto.DriveReq;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;

import java.sql.SQLException;
import java.util.List;

public interface DriveService {

    void save(DriveReq d) throws SQLException;

    void delete(Long id) throws SQLException;

    DriveResp findById(Long id);

    List<DriveResp> findAll();
}
