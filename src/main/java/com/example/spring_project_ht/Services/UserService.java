package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.DAO.UserDaoJPA;
import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.DAO.UserDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
public class UserService {
    private UserDao userDao;
    private UserDaoJPA userDaoJPA;

    public UserService(UserDao userDao, UserDaoJPA userDaoJPA) {
        this.userDao = userDao;
        this.userDaoJPA = userDaoJPA;
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

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    public User getUserByIdJpa(int id) {
        return userDaoJPA.getUserByIdUser(id);
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
