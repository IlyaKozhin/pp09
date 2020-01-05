package com.springboot.jmtask.dao;

import com.springboot.jmtask.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
   void addUser(User user);
   List<User> listUsers();
   void deleteUser(String username);
   void updateUser(User user);

    UserDetails getUserByUsername(String username);
}
