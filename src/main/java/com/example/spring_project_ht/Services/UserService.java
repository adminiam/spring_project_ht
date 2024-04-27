package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.Dao.UserDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String addUser(User user) {
        userDao.addUser(user.getIdUser(), user);
        return new UserResponse("User with ID " + user.getIdUser() + " added successfully.").getMessage();
    }

    public String removeUser(int id) {
        userDao.removeUser(id);
        return "User with ID " + id + " removed successfully.";

    }

    public List<Integer> getUserTasks(int idUser) {
        return userDao.getUserTasks(idUser);
    }


    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

@Getter
@Setter
    private static class UserResponse {
        private String message;
        public UserResponse(String message) {
            this.message = message;
        }
    }
}
