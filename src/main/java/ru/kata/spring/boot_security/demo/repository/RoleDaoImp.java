package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAll(Collection<Role> roles) {
        for (Role role: roles) {
            entityManager.persist(role);
            entityManager.flush();
        }
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
        entityManager.flush();
    }

    @Override
    public void deleteAll(Collection<Role> roles){
        for (Role role: roles) {
            entityManager.remove(role);
            entityManager.flush();
        }
    }

}
