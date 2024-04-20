package com.example.spring_project_ht.DAO;

import com.example.spring_project_ht.Models.User;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/taskManagement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";

    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (idUser,userName) VALUES (?,?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE idUser = ?";
    private static final String GET_TASK_QUERY = "SELECT idTask FROM users WHERE idUser = ?";
    private static final String CHANGE_TASK_QUERY = "UPDATE users SET idTask = ? WHERE userId = ?";
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("idUser");
                String name = resultSet.getString("userName");
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
}
