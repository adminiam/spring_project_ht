package com.example.spring_project_ht.DAO;

import com.example.spring_project_ht.Models.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "application.component", havingValue = "JPA")
public class UserDaoJpaComponent implements DBConectivity {
    private final UserDaoJPA userDaoJPA;

    public UserDaoJpaComponent(UserDaoJPA userDaoJPA) {
        this.userDaoJPA = userDaoJPA;
    }

    @Override
    public User getUserById(int id) {
        return userDaoJPA.getUserByIdUser(id);
    }
}
