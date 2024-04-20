package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.Task;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private Map<Integer, Task> tasks = new HashMap<>();

    private UserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public String createTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return "Task with ID " + task.getId() + " created successfully.";
        } else {
            return "Task with ID " + task.getId() + " already exists.";
        }
    }

    public List<Task> getUserTasks(int idUser) {
        List<Task> tasks = new ArrayList<>();

        for (int idTask : userService.getUserTasks(idUser)) {
            tasks.add(getTaskById(idTask));
        }
        return tasks;
    }

    public String setUserTask(int idUser, int idTask) {
        userService.getUserDao().updateIdTask(idTask, idUser);
        return "Task with ID " + idTask + " set for user with ID " + idUser + " successfully.";
    }

    public String changeStatus(int idTask, Task.Status status) {
        if (tasks.containsKey(idTask)) {
            tasks.get(idTask).setStatus(status);
            return "Status of task with ID " + idTask + " changed successfully.";
        } else {
            return "There is no such task with ID " + idTask;
        }
    }

    public List<Task> filterTasksByStatus(int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getStatus));
    }

    public List<Task> filterTasksByPriority(int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getPriority));
    }

    public List<Task> filterTasksByDeadline(int idUser) {
        return filterTasks(idUser, Comparator.comparing(Task::getDeadline));
    }

    private List<Task> filterTasks(int idUser, Comparator<Task> comparator) {
        List<Task> userTasks = getUserTasks(idUser);
        if (userTasks != null) {
            return userTasks.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User tasks not found.");
        }
    }

    public List<Task> searchTasksByStatus(int idUser, Task.Status status) {
        List<Task> userTasks = getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> searchTasksByPriority(int idUser, Task.Priority priority) {
        List<Task> userTasks = getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public List<Task> searchTasksByDeadline(int idUser, String deadline) {
        List<Task> userTasks = getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getDeadline().equals(deadline))
                .collect(Collectors.toList());
    }
}
