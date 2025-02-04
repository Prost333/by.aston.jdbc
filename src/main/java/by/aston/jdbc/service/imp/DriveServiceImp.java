package by.aston.jdbc.service.imp;

import by.aston.jdbc.dto.DriveReq;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.exeption.EntityNotFoundException;
import by.aston.jdbc.mapper.DriveMapper;
import by.aston.jdbc.repository.DriveDao;
import by.aston.jdbc.repository.UserDao;
import by.aston.jdbc.service.DriveService;
import by.aston.jdbc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriveServiceImp implements DriveService {
    private DriveDao driveDao;
    private UserDao userService;
    private DriveMapper driveMapper;

    @Override
    public void save(DriveReq d) throws SQLException {
        Drive drive=driveMapper.toRequest(d);
        drive.setUser(userService.findById(d.getUserId()));
        driveDao.addDrive(drive);
    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            driveDao.deleteDrive(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not Found");
        }
    }

    @Override
    public DriveResp findById(Long id) throws EntityNotFoundException {
        return driveDao.findById(id);
    }

    @Override
    public List<DriveResp> findAll() throws EntityNotFoundException{
        return driveDao.findAllToResponse();
    }
}
