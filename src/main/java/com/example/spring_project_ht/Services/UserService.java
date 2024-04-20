package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.DAO.UserDao;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String addUser(User user) {
        userDao.addUser(user.getId(), user);
        return new UserResponse("User with ID " + user.getId() + " added successfully.").getMessage();
    }

    public String removeUser(int id) {
        userDao.removeUser(id);
        return "User with ID " + id + " removed successfully.";

    }

    public List<Integer> getUserTasks(int idUser) {
        return userDao.getUserTasks(idUser);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    private static class UserResponse {
        private String message;

        public UserResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
