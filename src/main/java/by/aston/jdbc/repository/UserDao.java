package by.aston.jdbc.repository;

import by.aston.jdbc.connection.DatabaseConnection;
import by.aston.jdbc.entity.User;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO users" +
            "  (name, surname) VALUES " +
            " (?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,surname from Users where id =?";
    private static final String SELECT_ALL_USER = "select * from Users";
    private static final String DELETE_USER_SQL = "delete from Users where id = ?;";
    private static final String UPDATE_USER_SQL = "update Users set name = ?,surname= ?, where id = ?;";
    private static final String SELECT_USER_NAME_BY_ID = "SELECT name FROM Users WHERE id = ?";

    public UserDao() {}

    public void addUser(User user) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw  new EntityNotFoundException("some problem");
        }catch (EntityNotFoundException n){
            throw  new EntityNotFoundException("error");
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setLong(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


    public boolean deleteUser(Long id) throws SQLException {
        User user=findById(id);
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public User findById(Long id) {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                user = new User(id, name, surname);
            }
            if (user==null){
                throw new EntityNotFoundException("user not found");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER)) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                users.add(new User(id, name, surname));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    public String findUserNameById(Long id) {
        String userName = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_NAME_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userName = rs.getString("name");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return userName;
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
