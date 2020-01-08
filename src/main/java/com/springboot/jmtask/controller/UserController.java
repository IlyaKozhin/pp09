package com.springboot.jmtask.controller;

import com.springboot.jmtask.model.Role;
import com.springboot.jmtask.model.User;
import com.springboot.jmtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String modifyUsers(ModelMap modelMap) {
       /* User user = new  User("admin", "1","qwe@gmail.com", null);
        Role role1 = new Role("admin",user);
        user.setRoles(new HashSet(Arrays.asList(role1)));
        userService.add(user);*/
        /*List<User> usersList = userService.listUsers();
        List<SimpleUser> users = new ArrayList<>();
        for(User user: usersList) {
            users.add(new SimpleUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getRoles().get(0)));
        }
        modelMap.addAttribute("users", users);*/
        return "adminRest";
    }
    class SimpleUser {
        private String username;
        private String password;
        private String email;
        private String role;

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

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String viewUserPage(ModelMap modelMap) {
        return "user";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest request) {
        userService.delete(request.getParameter("update"));
        return "redirect:/admin";

    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        String name = request.getParameter("InputLogin");
        String email = request.getParameter("InputEmail");
        String password = request.getParameter("InputPassword");
        String role = request.getParameter("InputRole");
        User user = new User(name, password, email, null);
        if (role == "") {
            role = "ROLE_user";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(role, user));
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";

    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request) {
        String email = request.getParameter("modalEmail" );
        String password = request.getParameter("modalPassword");
        String name = request.getParameter("modalLogin");
        String role = request.getParameter("modalRole");
        Set<String> roles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        User user = new User(name, password,email, null);
        if (role == null) {
            role = "ROLE_user";
        }
       roles.add(role);
        for (String roleString : roles) {
            userRoles.add(new Role(roleString, user));
        }
        user.setRoles(userRoles);
        userService.delete(name);
        userService.add(user);

        return "redirect:/admin";
    }
}