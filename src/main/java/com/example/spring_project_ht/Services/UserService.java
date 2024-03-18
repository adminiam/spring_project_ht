package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            System.out.println("User with ID " + user.getId() + " already exists.");
        }
    }

    public void removeUser(int id) {
        users.remove(id);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public List<Task> getUserTasks(int idUser) {
        if (users.get(idUser) != null)
            return users.get(idUser).getTasks();
        else return null;
    }
}
