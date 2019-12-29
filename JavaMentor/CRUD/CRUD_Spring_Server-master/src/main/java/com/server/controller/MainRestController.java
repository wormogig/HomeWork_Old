package com.server.controller;

import com.server.model.User;
import com.server.repository.UserRepo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRestController {

    private final UserRepo userRepo;

    public MainRestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(
            value = "/users"
    )
    public List<User> getUsers() {
        return userRepo.list();
    }

    @GetMapping(
            value = "/user/{id}"
    )
    public User getUser(@PathVariable("id") long id) {
        return userRepo.getById(id);
    }

    @PostMapping(
            value = "/admin/add"
    )
    public User add(@RequestBody User user) {
        return userRepo.add(user);
    }

    @DeleteMapping(
            value = "/admin/delete/{id}"
    )
    public String delete(@PathVariable("id") long id) {
        String str = "";
        try {
            userRepo.delete(id);
            str = "User with id = " + id + " has been deleted";
        } catch (Exception e) {
            str = "User with id = " + id + " has not been deleted";
        }
        return str;
    }

    @PutMapping(
            value = "/admin/edit"
    )
    public String edit(@RequestBody User user) {
        String str = "";
        try {
            userRepo.edit(user);
            str = "User with id = " + user.getId() + " has been updated";
        } catch (Exception e) {
            str = "User with id = " + user.getId() + " has not been updated";
        }
        return str;
    }

    @GetMapping(
            value = "/login"
    )
    public User auth(@RequestParam(name = "login") String login) {
        return userRepo.getByName(login);
    }
}
