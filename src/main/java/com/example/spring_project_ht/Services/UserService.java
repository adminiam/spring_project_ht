package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<Integer, User> users = new HashMap<>();

    public String addUser(User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return new UserResponse("User with ID " + user.getId() + " added successfully.").getMessage();
        } else {
            return new UserResponse("User with ID " + user.getId() + " already exists.").getMessage();
        }
    }

    public String removeUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return "User with ID " + id + " removed successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public List<Task> getUserTasks(int idUser) {
        if (users.get(idUser) != null)
            return users.get(idUser).getTasks();
        else return Collections.emptyList();
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
