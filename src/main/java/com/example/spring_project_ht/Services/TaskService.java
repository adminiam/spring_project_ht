package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private Map<Integer, Task> tasks = new HashMap<>();

    private UserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public String createTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return "Task with ID " + task.getId() + " created successfully.";
        } else {
            return "Task with ID " + task.getId() + " already exists.";
        }
    }
    public String setUserTask(int idUser,int idTask) {
        if (userService.getUsers().containsKey(idUser)) {
            User user = userService.getUsers().get(idUser);
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

    public String changeStatus(int idTask,Task.Status status) {
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
        List<Task> userTasks = userService.getUserTasks(idUser);
        if (userTasks != null) {
            return userTasks.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User tasks not found.");
        }
    }

    public List<Task> searchTasksByStatus(int idUser,Task.Status status) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> searchTasksByPriority(int idUser,Task.Priority priority) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public List<Task> searchTasksByDeadline(int idUser, String deadline) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        return userTasks.stream()
                .filter(task -> task.getDeadline().equals(deadline))
                .collect(Collectors.toList());
    }
}
