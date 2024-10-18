package com.example.SpringBootTest.dao;

import com.example.SpringBootTest.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<User> findAll() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User tempUser = em.find(User.class, id);
        tempUser.setName(user.getName());
        tempUser.setAge(user.getAge());
        tempUser.setEmail(user.getEmail());
        em.merge(tempUser);
    }

}
