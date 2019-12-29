package com.server.repository;

import com.server.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> list() {
        return entityManager.createQuery("FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String login) {
        Query query = entityManager.createQuery("FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }
}
