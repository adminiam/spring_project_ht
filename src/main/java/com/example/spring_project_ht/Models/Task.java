package com.example.spring_project_ht.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id_task")
    private int id;
    private String name;
    private String description;
    private String deadline;
    private Priority priority;
    private Status status = Status.New;

    public enum Priority {High, Medium, Low}

    public enum Status {New, InProcess, Finished, Scheduled}

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
