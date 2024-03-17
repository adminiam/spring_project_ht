package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Models.Task;
import com.example.spring_project_ht.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private Map<Integer, Task> tasks = new HashMap<>();

    @Autowired
    private UserService userService;

    public void createTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else {
            System.out.println("Task with ID " + task.getId() + " already exists.");
        }
    }


    public boolean setUserTask(int idUser, int idTask) {
        if (userService.getUsers().get(idUser) != null) {
            User user = userService.getUsers().get(idUser);
            if (tasks.get(idTask) != null) {
                user.setTasks(tasks.get(idTask));
            } else {
                System.out.println("There is no such task. Try again");
                throw new IllegalArgumentException();
            }
        } else {
            System.out.println("There is no such user. Try again");
            throw new IllegalArgumentException();
        }
        return true;
    }

    public void changeStatus(int idTask, Task.Status status) {
        if (tasks.get(idTask) != null)
            tasks.get(idTask).setStatus(status);
        else System.out.println("There is no such task. Try again");
    }

    public void filterTasksByStatus(int idUser) {
        filterAndPrintTasks(idUser, "Filtered by status", Comparator.comparing(Task::getStatus));
    }

    public void filterTasksByPriority(int idUser) {
        filterAndPrintTasks(idUser, "Filtered by priority", Comparator.comparing(Task::getPriority));
    }

    public void filterTasksByDeadline(int idUser) {
        filterAndPrintTasks(idUser, "Filtered by deadline", Comparator.comparing(Task::getDeadline));
        System.out.print("\n");
    }

    private void filterAndPrintTasks(int idUser, String message, Comparator<Task> comparator) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        if (userTasks != null) {
            System.out.println(message);
            userTasks.stream()
                    .sorted(comparator)
                    .forEach(System.out::println);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void printTasks(List<Task> tasks, String message) {
        if (!tasks.isEmpty()) {
            System.out.println(message);
            tasks.forEach(task -> {
                System.out.println(task.getId() +
                        "\n" + task.getName() +
                        "\n" + task.getDescription() +
                        "\n" + task.getDeadline() +
                        "\n" + task.getStatus()+"\n");
            });
        } else {
            System.out.println("No tasks found."+"\n");
        }
    }

    public void searchTasksByStatus(int idUser, Task.Status status) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        List<Task> filteredTasks = userTasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
        printTasks(filteredTasks, "Tasks with status '" + status + "':");
    }

    public void searchTasksByPriority(int idUser, Task.Priority priority) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        List<Task> filteredTasks = userTasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
        printTasks(filteredTasks, "Tasks with priority '" + priority + "':");
    }

    public void searchTasksByDeadline(int idUser, String deadline) {
        List<Task> userTasks = userService.getUserTasks(idUser);
        List<Task> filteredTasks = userTasks.stream()
                .filter(task -> task.getDeadline().equals(deadline))
                .collect(Collectors.toList());
        printTasks(filteredTasks, "Tasks with deadline '" + deadline + "':");
    }
}
