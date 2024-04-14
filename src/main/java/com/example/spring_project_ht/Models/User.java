package com.example.spring_project_ht.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "user_name")
    private String name;
    @ManyToMany
    private List<Task> tasks;


    public User(int id, String name) {
        this.idUser = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                '}';
    }
}
