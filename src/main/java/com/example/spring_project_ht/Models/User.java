package com.example.spring_project_ht.Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;

    private List<Task> tasks = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setTasks(Task task) {
        tasks.add(task);
    }

    public void checkTaskStatus(int idTask) {
        for (Task task : tasks) {
            if (task.getId() == idTask) {
                System.out.println(task);
                System.out.print("\n");
                break;
            }
        }
    }

    public void checkAllTasksStatus() {
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.print("\n");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
