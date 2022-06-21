package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    User findUserByEmail(String name);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);

    User findUserById(Long id);

    void saveAndFlush(User user);
}
