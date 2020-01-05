package com.springboot.jmtask.dao;

import com.springboot.jmtask.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        //entityManager.p
    }

    @Override
    public void updateUser(User user) {
        entityManager.unwrap(Session.class).merge(user);
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        return entityManager
                .createQuery("FROM User WHERE username = :username",User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager
                .createQuery("from User",User.class)
                .getResultList();
    }

    @Override
    public void deleteUser(String username) {
        User user = entityManager.find(User.class, username);
        entityManager.remove(user);
    }
}
