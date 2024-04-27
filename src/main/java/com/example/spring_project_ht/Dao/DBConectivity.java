package com.example.spring_project_ht.Dao;

import com.example.spring_project_ht.Models.User;

public interface DBConectivity {

    User getUserById(int id);
}