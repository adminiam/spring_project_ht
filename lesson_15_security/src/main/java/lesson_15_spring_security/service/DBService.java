package lesson_15_spring_security.service;

import lesson_15_spring_security.repo.UserRepo;
import lesson_15_spring_security.controller.LoginController;
import lesson_15_spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DBService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LoginController redirectController;

    public User getUserByName(String name) {
        User user = userRepo.getUserByName(name);
        if (user == null) {
            redirectController.login();
            return null;
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
