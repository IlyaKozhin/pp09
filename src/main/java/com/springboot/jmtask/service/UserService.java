package com.springboot.jmtask.service;



import com.springboot.jmtask.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void delete(String username);

    void update(User user);
}
