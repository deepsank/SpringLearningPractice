package com.deepak.microservices.Userservice.services.impl;

import com.deepak.microservices.Userservice.entities.Rating;
import com.deepak.microservices.Userservice.entities.User;
import com.deepak.microservices.Userservice.exceptions.ResourceNotFoundException;
import com.deepak.microservices.Userservice.repositories.UserRepository;
import com.deepak.microservices.Userservice.services.UserService;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID not found on the server!!! :-- ID:-- " + userId));
        //http://localhost:8083/ratings/users/26b771df-efaf-4937-a3ce-0bc2f4f42255
        ArrayList<Rating> rating = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
        logger.info("{} ",rating);
        user.setRatings(rating);
        return user;
    }
}
