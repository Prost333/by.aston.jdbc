package by.aston.jdbc.repository;

import by.aston.jdbc.connection.DatabaseConnection;
import by.aston.jdbc.entity.City;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CityDao {
    private static final String INSERT_CITY_SQL = "INSERT INTO city" +
            "  (name, country) VALUES " +
            " (?, ?);";

    private static final String SELECT_CITY_BY_ID = "select name,country from city where id =?";
    private static final String SELECT_ALL_CITY = "select name, country from city";
    private static final String DELETE_CITY_SQL = "delete from city where id = ?;";
    private static final String UPDATE_CITY_SQL = "update city set name = ?,country= ? where id = ?;";

    public CityDao() {}

    public void addCity(City city) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY_SQL)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }
    }

    public boolean updateCity(City city) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CITY_SQL);) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountry());
            statement.setLong(3, city.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteCity(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CITY_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public City findById(Long id) {
        City city = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String country = rs.getString("country");
                city = new City(id, name, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }
        return city;
    }

    public List<City> findByAll() {
        List<City> cities = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CITY)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                cities.add(new City(null, name, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }
        return cities;
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
