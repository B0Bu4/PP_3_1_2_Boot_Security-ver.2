package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public interface RoleService {
    void saveAll(Collection<Role> roles);

    void save(Role role);

    void deleteAll(Collection<Role> roles);

    Collection<Role> findAllRolesByNameOnDataBase(String[] roles);
}
