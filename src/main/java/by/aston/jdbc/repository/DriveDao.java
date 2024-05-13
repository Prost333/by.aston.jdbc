package by.aston.jdbc.repository;

import by.aston.jdbc.connection.DatabaseConnection;
import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DriveDao {
    private static final String INSERT_DRIVE_SQL = "INSERT INTO drive" +
            "  ( km, time, surge, city, rate, paid_time, door_to_door, paid_submission, dop_sum, user_id) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_DRIVE_BY_ID = "select d.km, d.time, d.surge, d.city, d.rate, d.paid_time, " +
            "d.door_to_door, d.paid_submission, d.dop_sum, u.name " +
            "FROM drive d " +
            "JOIN users u ON d.user_id = u.id where d.id =?";
    private static final String SELECT_DRIVE = "select * from drive ";
    private static final String SELECT_ALL_DRIVE = "SELECT d.id, d.km, d.time, d.surge, d.city, d.rate, d.paid_time, \n" +
            "d.door_to_door, d.paid_submission, d.dop_sum, u.name \n" +
            "FROM drive d \n" +
            "JOIN users u ON d.user_id = u.id;";
    private static final String DELETE_DRIVE_SQL = "delete from drive where id = ?;";
    private static final String UPDATE_DRIVE_SQL = "update drive set km = ?, time = ?, surge = ?, city = ?, rate = ?, paid_time = ?, door_to_door = ?, paid_submission = ?, dop_sum = ?, user_id = ? where id = ?;";

    public DriveDao() {}

    public void addDrive(Drive drive) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRIVE_SQL)) {
            preparedStatement.setBigDecimal(1, drive.getKm());
            preparedStatement.setBigDecimal(2, drive.getTime());
            preparedStatement.setBigDecimal(3, drive.getSurge());
            preparedStatement.setString(4, drive.getCity());
            preparedStatement.setString(5, drive.getRate());
            preparedStatement.setBigDecimal(6, drive.getPaidTime());
            preparedStatement.setInt(7, drive.getDoorToDoor());
            preparedStatement.setBigDecimal(8, drive.getPaidSubmission());
            preparedStatement.setBigDecimal(9, drive.getDopSum());
            preparedStatement.setLong(10, drive.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }catch (EntityNotFoundException n){
            throw  new EntityNotFoundException("some problem");
        }
    }

    public boolean updateDrive(Drive drive) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DRIVE_SQL);) {
            statement.setBigDecimal(1, drive.getKm());
            statement.setBigDecimal(2, drive.getTime());
            statement.setBigDecimal(3, drive.getSurge());
            statement.setString(4, drive.getCity());
            statement.setString(5, drive.getRate());
            statement.setBigDecimal(6, drive.getPaidTime());
            statement.setInt(7, drive.getDoorToDoor());
            statement.setBigDecimal(8, drive.getPaidSubmission());
            statement.setBigDecimal(9, drive.getDopSum());
            statement.setLong(10, drive.getUserId());
            statement.setLong(11, drive.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteDrive(Long id) throws SQLException {
        DriveResp drive = findById(id);
        if (drive == null) {
            throw new EntityNotFoundException("Drive not found");
        }
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DRIVE_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public DriveResp findById(Long id) {
        DriveResp drive = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRIVE_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BigDecimal km = rs.getBigDecimal("km");
                BigDecimal time = rs.getBigDecimal("time");
                BigDecimal surge = rs.getBigDecimal("surge");
                String city = rs.getString("city");
                String rate = rs.getString("rate");
                BigDecimal paidTime = rs.getBigDecimal("paid_time");
                Integer DoorToDoor = rs.getInt("door_to_door");
                BigDecimal paidSubmission = rs.getBigDecimal("paid_submission");
                BigDecimal dopSum = rs.getBigDecimal("dop_sum");
                String userId = rs.getString("name");

                drive = new DriveResp(km, time, surge, city, rate, paidTime, DoorToDoor, paidSubmission, dopSum, userId);
            }
            if (drive==null){
                throw new EntityNotFoundException("drive not found");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return drive;
    }

    public List<Drive> findAll() {
        List<Drive> drives = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_DRIVE)) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                BigDecimal km = resultSet.getBigDecimal("km");
                BigDecimal time = resultSet.getBigDecimal("time");
                BigDecimal surge = resultSet.getBigDecimal("surge");
                String city = resultSet.getString("city");
                String rate = resultSet.getString("rate");
                BigDecimal paidTime = resultSet.getBigDecimal("paid_time");
                Integer DoorToDoor = resultSet.getInt("door_to_door");
                BigDecimal paidSubmission = resultSet.getBigDecimal("paid_submission");
                BigDecimal dopSum = resultSet.getBigDecimal("dop_sum");
                Long userId = resultSet.getLong("user_id");

                drives.add(new Drive(id, km, time, surge, city, rate, paidTime, DoorToDoor, paidSubmission, dopSum, userId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return drives;
    }
    public List<DriveResp> findAllToResponse() {
        List<DriveResp> drives = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DRIVE)) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                BigDecimal km = resultSet.getBigDecimal("km");
                BigDecimal time = resultSet.getBigDecimal("time");
                BigDecimal surge = resultSet.getBigDecimal("surge");
                String city = resultSet.getString("city");
                String rate = resultSet.getString("rate");
                BigDecimal paidTime = resultSet.getBigDecimal("paid_time");
                Integer DoorToDoor = resultSet.getInt("door_to_door");
                BigDecimal paidSubmission = resultSet.getBigDecimal("paid_submission");
                BigDecimal dopSum = resultSet.getBigDecimal("dop_sum");
               String userId = resultSet.getString("name");

                drives.add(new DriveResp(km, time, surge, city, rate, paidTime, DoorToDoor, paidSubmission, dopSum,userId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return drives;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
