package com.example.spring_project_ht.Controllers;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public String createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/setUserTask")
    public String setUserTask(@RequestParam int idUser, @RequestParam int idTask) {
        return taskService.setUserTask(idUser, idTask);
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam int idTask, @RequestParam Task.Status status) {
        return taskService.changeStatus(idTask, status);
    }

    @GetMapping("/filterByStatus/{idUser}")
    public List<Task> filterTasksByStatus(@PathVariable int idUser) {
        return taskService.filterTasksByStatus(idUser);
    }

    @GetMapping("/filterByPriority/{idUser}")
    public List<Task> filterTasksByPriority(@PathVariable int idUser) {
        return taskService.filterTasksByPriority(idUser);
    }

    @GetMapping("/filterByDeadline/{idUser}")
    public List<Task> filterTasksByDeadline(@PathVariable int idUser) {
        return taskService.filterTasksByDeadline(idUser);
    }

    @GetMapping("/searchByStatus/{idUser}")
    public List<Task> searchTasksByStatus(@PathVariable int idUser, @RequestParam Task.Status status) {
        return taskService.searchTasksByStatus(idUser, status);
    }

    @GetMapping("/searchByPriority/{idUser}")
    public List<Task> searchTasksByPriority(@PathVariable int idUser, @RequestParam Task.Priority priority) {
        return taskService.searchTasksByPriority(idUser, priority);
    }

    @GetMapping("/searchByDeadline/{idUser}")
    public List<Task> searchTasksByDeadline(@PathVariable int idUser, @RequestParam String deadline) {
        return taskService.searchTasksByDeadline(idUser, deadline);
    }
}
