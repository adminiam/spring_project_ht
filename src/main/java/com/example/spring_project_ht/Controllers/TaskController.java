package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private Map<Integer, Task> tasks = new HashMap<>();

    private UserController userController;

    public TaskController(UserController userController) {
        this.userController = userController;
    }

    @PostMapping("/createTask")
    public @ResponseBody String createTask(@RequestBody Task task) {
        if (!tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return "Task with ID " + task.getId() + " created successfully.";
        } else {
            return "Task with ID " + task.getId() + " already exists.";
        }
    }

    @PostMapping("/setUserTask")
    public @ResponseBody String setUserTask(@RequestParam int idUser, @RequestParam int idTask) {
        if (userController.getUsers().containsKey(idUser)) {
            User user = userController.getUsers().get(idUser);
            if (tasks.containsKey(idTask)) {
                user.setTasks(tasks.get(idTask));
                return "Task with ID " + idTask + " set for user with ID " + idUser + " successfully.";
            } else {
                return "There is no such task with ID " + idTask;
            }
        } else {
            return "There is no such user with ID " + idUser;
        }
    }

    @PostMapping("/changeStatus")
    public @ResponseBody String changeStatus(@RequestParam int idTask, @RequestParam Task.Status status) {
        if (tasks.containsKey(idTask)) {
            tasks.get(idTask).setStatus(status);
            return "Status of task with ID " + idTask + " changed successfully.";
        } else {
            return "There is no such task with ID " + idTask;
        }
    }

    @GetMapping("/filterTasksByStatus")
    public @ResponseBody List<Task> filterTasksByStatus(@RequestParam int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getStatus));
    }

    @GetMapping("/filterTasksByPriority")
    public @ResponseBody List<Task> filterTasksByPriority(@RequestParam int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getPriority));
    }

    @GetMapping("/filterTasksByDeadline")
    public @ResponseBody List<Task> filterTasksByDeadline(@RequestParam int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getDeadline));
    }

    private List<Task> filterTasks(int idUser, Comparator<Task> comparator) {
        List<Task> userTasks = userController.getUserTasks(idUser);
        if (userTasks != null) {
            return userTasks.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User tasks not found.");
        }
    }

    @GetMapping("/searchTasksByStatus")
    public @ResponseBody List<Task> searchTasksByStatus(@RequestParam int idUser, @RequestParam Task.Status status) {
        List<Task> userTasks = userController.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchTasksByPriority")
    public @ResponseBody List<Task> searchTasksByPriority(@RequestParam int idUser, @RequestParam Task.Priority priority) {
        List<Task> userTasks = userController.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchTasksByDeadline")
    public @ResponseBody List<Task> searchTasksByDeadline(@RequestParam int idUser, @RequestParam String deadline) {
        List<Task> userTasks = userController.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getDeadline().equals(deadline))
                .collect(Collectors.toList());
    }
}
