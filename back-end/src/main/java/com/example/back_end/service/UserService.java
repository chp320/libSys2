package com.example.back_end.service;

import com.example.back_end.entity.User;
import com.example.back_end.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // reading users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // creating user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // reading an user
    public List<User> getUserByUserID(String userID) {
        return userRepository.findByUserIDContaining(userID);
    }

}
