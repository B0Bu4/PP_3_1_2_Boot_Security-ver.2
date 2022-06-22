package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void saveAndFlush(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Hibernate.initialize(user.getAuthorities());

        return user;
    }
}
