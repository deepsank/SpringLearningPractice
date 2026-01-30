package com.deepak.microservices.Userservice.services.impl;

import com.deepak.microservices.Userservice.entities.User;
import com.deepak.microservices.Userservice.exceptions.ResourceNotFoundException;
import com.deepak.microservices.Userservice.repositories.UserRepository;
import com.deepak.microservices.Userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        //Generate unique userId for each user
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given ID not found on the server!!! :-- ID:-- "+userId));
    }
}
