package com.example.spring_project_ht;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import com.example.spring_project_ht.Services.TaskService;
import com.example.spring_project_ht.Services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringProjectHtApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringProjectHtApplication.class, args);
        TaskService taskService = applicationContext.getBean(TaskService.class);
        UserService userService = applicationContext.getBean(UserService.class);


        userService.addUser(new User(1, "John"));
        userService.addUser(new User(2, "Tom"));
        userService.addUser(new User(3, "Smith"));
        userService.addUser(new User(4, "Max"));
        //check if exists
        userService.addUser(new User(1, "John"));
        userService.removeUser(4);


        taskService.createTask(new Task(1, "HT1", "Solve problem1",
                "tomorrow", Task.Priority.High));
        taskService.createTask(new Task(2, "HT2", "Solve problem2",
                "the day after tomorrow", Task.Priority.Medium));
        taskService.createTask(new Task(3, "HT3", "Solve problem3",
                "one week", Task.Priority.Low));
        //check if exists
        taskService.createTask(new Task(3, "HT3", "Solve problem3",
                "two weeks", Task.Priority.Low));
        //set task to user
        taskService.setUserTask(1,1);
        taskService.setUserTask(1,2);


        User user = userService.getUsers().get(1);
        user.checkAllTasksStatus();
        user.checkTaskStatus(1);

        taskService.changeStatus(3, Task.Status.InProcess);

        taskService.filterTasksByDeadline(1);

        taskService.searchTasksByStatus(1, Task.Status.InProcess);
        taskService.searchTasksByStatus(1, Task.Status.New);
    }

}
