package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserDao {

    User findUserByEmail(String email);

    User findUserById(Long id);

    List<User> findAll();

    void save(User user);

    void saveAndFlush(User user);

    void deleteById(Long id);

}
