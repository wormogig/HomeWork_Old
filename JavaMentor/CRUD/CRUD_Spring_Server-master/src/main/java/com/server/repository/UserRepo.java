package com.server.repository;

import com.server.model.User;

import java.util.List;

public interface UserRepo {
    List<User> list();
    User add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    User getByName(String login);
}
