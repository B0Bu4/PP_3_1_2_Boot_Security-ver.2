package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;


public interface UserService extends UserDetailsService {
    User findUserByEmail(String name);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);

    User findUserById(Long id);

    Set<Role> getRoleToSet(String[] roles);

    void saveAndFlush(User user);
}
