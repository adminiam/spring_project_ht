package com.example.spring_project_ht.Dao;


import com.example.spring_project_ht.Models.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "application.component", havingValue = "JDBC")
public class UserDao implements DBConectivity {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/taskManagement";
    private static final String JDBC_USER = System.getenv("login");
    private static final String JDBC_PASSWORD = System.getenv("pass");

    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (id_user,user_name) VALUES (?,?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id_user = ?";
    private static final String GET_TASK_QUERY = "SELECT idTask FROM users WHERE id_user = ?";
    private static final String CHANGE_TASK_QUERY = "UPDATE users SET idTask = ? WHERE id_user = ?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id_user = ?";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String name = resultSet.getString("user_name");
                users.add(new User(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Integer> getUserTasks(int id) {
        List<Integer> tasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TASK_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tasks.add(resultSet.getInt("idTask"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateIdTask(int userId, int newIdTask) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(CHANGE_TASK_QUERY)) {
            preparedStatement.setInt(1, newIdTask);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(int idUser, User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("This user is already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int idUser) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        User user;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getInt("id_user"), resultSet.getString("user_name"));
                    return user;
                } else {
                    System.out.println("No such user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new User();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
