package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.Services.UserService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Value("${spring.profiles.active}")
    private String activeProfile;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/remove/{id}")
    public String removeUser(@PathVariable int id) {
        return userService.removeUser(id);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        if ("JDBC".equals(activeProfile)) {
            return userService.getUserById(id);
        } else if ("JPA".equals(activeProfile)) {
            return userService.getUserByIdJpa(id);
        } else {
            throw new IllegalArgumentException("Invalid profile: " + activeProfile);
        }
    }



}
