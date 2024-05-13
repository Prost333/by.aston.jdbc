package by.aston.jdbc.repository;

import by.aston.jdbc.connection.DatabaseConnection;
import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NameRatesDao {
    private static final String INSERT_NAMERATES_SQL = "INSERT INTO namerates" +
            "  (name) VALUES " +
            " (?);";

    private static final String SELECT_NAMERATES_BY_ID = "select id,name from namerates where id =?";
    private static final String SELECT_ALL_NAMERATES = "select * from namerates";
    private static final String DELETE_NAMERATES_SQL = "delete from namerates where id = ?;";
    private static final String UPDATE_NAMERATES_SQL = "update namerates set name = ? where id = ?;";

    public NameRatesDao() {
    }

    public void addNameRates(NameRates nameRates) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NAMERATES_SQL)) {
            preparedStatement.setString(1, nameRates.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }
    }

    public boolean updateNameRates(NameRates nameRates) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NAMERATES_SQL);) {
            statement.setString(1, nameRates.getName());
            statement.setLong(2, nameRates.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteNameRates(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NAMERATES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public NameRates findById(Long id) {
        NameRates nameRates = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAMERATES_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                nameRates = new NameRates(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return nameRates;
    }

    public List<NameRates> findAll() {
        List<NameRates> nameRates = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_NAMERATES)) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                nameRates.add(new NameRates(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return nameRates;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
