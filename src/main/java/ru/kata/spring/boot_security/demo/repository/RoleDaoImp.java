package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ArrayUtils;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.HashSet;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAll(Collection<Role> roles) {
        for (Role role : roles) {
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
    public void deleteAll(Collection<Role> roles) {
        for (Role role : roles) {
            entityManager.remove(role);
            entityManager.flush();
        }
    }

    @Override
    public Collection<Role> findAllRolesByNameOnDataBase(String[] roles) {
        if (ArrayUtils.contains(roles, "ROLE_ADMIN")) {
            return new HashSet<Role>(entityManager.createQuery("select roles from Role roles", Role.class).getResultList());
        } else {
            return new HashSet<Role>(entityManager
                    .createQuery("select roles from Role roles where roles.name=:role", Role.class)
                    .setParameter("role", "ROLE_USER")
                    .getResultList());
        }
    }

}
