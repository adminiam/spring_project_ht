package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.Services.UserService;
import com.example.spring_project_ht.Services.UserServiceDataBases;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserServiceDataBases userServiceDataBases;

    public UserController(UserService userService, UserServiceDataBases userServiceDataBases) {
        this.userService = userService;
        this.userServiceDataBases = userServiceDataBases;
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
        return userServiceDataBases.getUserById(id);
    }
    @GetMapping("/testError")
    public void testError() {
        throw new RuntimeException("Test error occurred!");
    }
}
