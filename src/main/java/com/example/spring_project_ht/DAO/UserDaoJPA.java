package com.example.spring_project_ht.DAO;

import com.example.spring_project_ht.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoJPA extends JpaRepository<User,Long> {
    User getUserByIdUser(Integer id);
}
