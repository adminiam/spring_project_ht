package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    private Map<Integer, User> users = new HashMap<>();

    @PostMapping("/addUser")
    public @ResponseBody String addUser(@RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return "User with ID " + user.getId() + " added successfully.";
        } else {
            return "User with ID " + user.getId() + " already exists.";
        }
    }

    @DeleteMapping("/removeUser")
    public @ResponseBody String removeUser(@RequestParam int id) {
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

    @GetMapping("/getUserTask")
    public @ResponseBody List<Task> getUserTasks(@RequestParam int idUser) {
        if (users.get(idUser) != null)
            return users.get(idUser).getTasks();
        else return Collections.emptyList();
    }
}
