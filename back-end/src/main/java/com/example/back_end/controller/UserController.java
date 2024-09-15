package com.example.back_end.controller;

import com.example.back_end.entity.User;
import com.example.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/search")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/register")
    public User createUser(@RequestBody User user) {
        System.out.println("(createUser) UserID: " + user.getUserID()
                            + ", userName: " + user.getUserName()
                            + ", userBirth: " + user.getUserBirth());
        return userService.createUser(user);
    }

    @GetMapping("/user/search/{userID}")
    public List<User> searchByUserID(@PathVariable String userID) {
        List<User> byUserID = userService.getUserByUserID(userID);
        return byUserID;
    }
}
