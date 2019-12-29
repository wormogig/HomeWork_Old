package com.client.service;

import com.client.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> allUsers();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    User getByName(String login);
}
