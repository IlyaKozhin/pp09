package com.springboot.jmtask.controller;

import com.springboot.jmtask.model.Role;
import com.springboot.jmtask.model.User;
import com.springboot.jmtask.service.UserService;
import com.springboot.jmtask.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    UserServiceImp userService;

    @PutMapping ("/users")
    public User updateUser(@RequestBody SimpleUser user) {
        Set<String> roles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        User userToAdd = new User(user.getUsername(), user.getPassword(), user.getEmail(), null);
        if (user.getRole() == "") {
            user.setRole("ROLE_user");
        }
        roles.add(user.getRole());
        for (String roleString : roles) {
            userRoles.add(new Role(roleString, userToAdd));
        }
        userToAdd.setRoles(userRoles);
        userService.update(userToAdd);
        return userToAdd;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody SimpleUser user) {
        Set<String> roles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        User userToAdd = new User(user.getUsername(), user.getPassword(), user.getEmail(), null);
        if (user.getRole() == "") {
            user.setRole("ROLE_user");
        }
        roles.add(user.getRole());
        for (String roleString : roles) {
            userRoles.add(new Role(roleString, userToAdd));
        }
        userToAdd.setRoles(userRoles);
        userService.delete(user.getUsername());
        userService.add(userToAdd);
        return userToAdd;
    }

    @GetMapping("/users")
    public List<SimpleUser> getUsers() {
        List<User> usersList = userService.listUsers();
        List<SimpleUser> users = new ArrayList<>();
        for (User user : usersList) {
            users.add(new SimpleUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles().get(0)));
        }
        return users;
    }

    static class SimpleUser {
        private String username;
        private String password;
        private String email;
        private String role;

        public SimpleUser() {
        }

        public void setRole(String role) {
            this.role = role;
        }

        public SimpleUser(String username, String password, String email, String role) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }
    }

}
