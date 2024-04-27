package com.example.spring_project_ht.Services;

import com.example.spring_project_ht.Dao.DBConectivity;
import com.example.spring_project_ht.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceDataBases {
    @Autowired
    private List<DBConectivity> dbConectivities;

    public User getUserById(int id){
        return dbConectivities.getFirst().getUserById(id);
    }

}
