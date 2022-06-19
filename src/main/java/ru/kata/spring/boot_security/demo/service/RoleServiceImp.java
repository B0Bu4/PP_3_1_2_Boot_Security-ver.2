package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleDao;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    @Transactional
    public void saveAll(Collection<Role> roles) {
        roleDao.saveAll(roles);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteAll(Collection<Role> roles){
        roleDao.deleteAll(roles);
    }
}
