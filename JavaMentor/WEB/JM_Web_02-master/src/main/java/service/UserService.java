package service;

import model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());


    public List<User> getAllUsers() {
        return null;
    }

    public User getUserById(Long id) {
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

    public void deleteAllUser() {

    }

    public boolean isExistsThisUser(User user) {
        return false;
    }

    public List<User> getAllAuth() {
        return  null;
    }

    public boolean authUser(User user) {
        return false;
    }

    public void logoutAllUsers() {

    }

    public boolean isUserAuthById(Long id) {
        return false;
    }

}
