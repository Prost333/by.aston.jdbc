package by.aston.jdbc.repository;

import by.aston.jdbc.connection.DatabaseConnection;
import by.aston.jdbc.entity.Rates;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RatesDao {
    private static final String INSERT_RATES_SQL = "INSERT INTO rates" +
            "  (name, city, landing, door_to_door, price_km_1, price_km_2, min_distance, max_distance, km_suburb, time_road, min_time_road, paid_waiting) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_RATES_BY_ID = "select id,name,city,landing,door_to_door,price_km_1,price_km_2,min_distance,max_distance,km_suburb,time_road,min_time_road,paid_waiting from rates where id =?";
    private static final String SELECT_ALL_RATES = "select * from rates";
    private static final String DELETE_RATES_SQL = "delete from rates where id = ?;";
    private static final String UPDATE_RATES_SQL = "update rates set name = ?,city= ?, landing =?, door_to_door=?, price_km_1=?, price_km_2=?, min_distance=?, max_distance=?, km_suburb=?, time_road=?, min_time_road=?, paid_waiting=? where id = ?;";

    public RatesDao() {}


    public void addRates(Rates rates) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RATES_SQL)) {
            preparedStatement.setString(1, rates.getName());
            preparedStatement.setString(2, rates.getCity());
            preparedStatement.setBigDecimal(3, rates.getLanding());
            preparedStatement.setBigDecimal(4, rates.getDoor_to_door());
            preparedStatement.setBigDecimal(5, rates.getPrice_km_1());
            preparedStatement.setBigDecimal(6, rates.getPrice_km_2());
            preparedStatement.setBigDecimal(7, rates.getMinDistance());
            preparedStatement.setBigDecimal(8, rates.getMaxDistance());
            preparedStatement.setBigDecimal(9, rates.getKm_suburb());
            preparedStatement.setBigDecimal(10, rates.getTimeRoad());
            preparedStatement.setBigDecimal(11, rates.getMinTimeRoad());
            preparedStatement.setBigDecimal(12, rates.getPaidWaiting());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }
    }

    public boolean updateRates(Rates rates) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RATES_SQL);) {
            statement.setString(1, rates.getName());
            statement.setString(2, rates.getCity());
            statement.setBigDecimal(3, rates.getLanding());
            statement.setBigDecimal(4, rates.getDoor_to_door());
            statement.setBigDecimal(5, rates.getPrice_km_1());
            statement.setBigDecimal(6, rates.getPrice_km_2());
            statement.setBigDecimal(7, rates.getMinDistance());
            statement.setBigDecimal(8, rates.getMaxDistance());
            statement.setBigDecimal(9, rates.getKm_suburb());
            statement.setBigDecimal(10, rates.getTimeRoad());
            statement.setBigDecimal(11, rates.getMinTimeRoad());
            statement.setBigDecimal(12, rates.getPaidWaiting());
            statement.setLong(13, rates.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


    public boolean deleteRates(Long id) throws SQLException {
        Rates rates=findById(id);
        if (rates==null){
            throw  new EntityNotFoundException("Rate not found");
        }
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RATES_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public Rates findById(Long id) {
        Rates rates = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RATES_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                BigDecimal landing = rs.getBigDecimal("landing");
                BigDecimal door_to_door = rs.getBigDecimal("door_to_door");
                BigDecimal price_km_1 = rs.getBigDecimal("price_km_1");
                BigDecimal price_km_2 = rs.getBigDecimal("price_km_2");
                BigDecimal min_distance = rs.getBigDecimal("min_distance");
                BigDecimal max_distance = rs.getBigDecimal("max_distance");
                BigDecimal km_suburb = rs.getBigDecimal("km_suburb");
                BigDecimal time_road = rs.getBigDecimal("time_road");
                BigDecimal min_time_road = rs.getBigDecimal("min_time_road");
                BigDecimal paid_waiting = rs.getBigDecimal("paid_waiting");
                rates = new Rates(id, name, city, landing, door_to_door, price_km_1, price_km_2, min_distance, max_distance, km_suburb, time_road, min_time_road, paid_waiting);
            }
            if (rates==null){
                throw  new EntityNotFoundException("Rate not found");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rates;
    }

    public List<Rates> findAll() {
        List<Rates> rates = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_RATES)) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                BigDecimal landing = resultSet.getBigDecimal("landing");
                BigDecimal door_to_door = resultSet.getBigDecimal("door_to_door");
                BigDecimal price_km_1 = resultSet.getBigDecimal("price_km_1");
                BigDecimal price_km_2 = resultSet.getBigDecimal("price_km_2");
                BigDecimal min_distance = resultSet.getBigDecimal("min_distance");
                BigDecimal max_distance = resultSet.getBigDecimal("max_distance");
                BigDecimal km_suburb = resultSet.getBigDecimal("km_suburb");
                BigDecimal time_road = resultSet.getBigDecimal("time_road");
                BigDecimal min_time_road = resultSet.getBigDecimal("min_time_road");
                BigDecimal paid_waiting = resultSet.getBigDecimal("paid_waiting");

                rates.add(new Rates(id, name, city, landing, door_to_door, price_km_1, price_km_2, min_distance, max_distance, km_suburb, time_road, min_time_road, paid_waiting));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rates;
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
