package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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
}
