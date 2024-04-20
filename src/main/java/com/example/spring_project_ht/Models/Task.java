package com.example.spring_project_ht.Models;

public class Task {
    private int id;
    private String name;
    private String description;
    private String deadline;
    private Priority priority;
    private Status status = Status.New;

    public Task(Integer id, String name, String description, String deadline, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public enum Priority {High, Medium, Low}

    public enum Status {New, InProcess, Finished, Scheduled}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
