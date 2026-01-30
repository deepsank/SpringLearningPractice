package com.deepak.microservices.Userservice.services;

import com.deepak.microservices.Userservice.entities.User;

import java.util.List;

public interface UserService {

    //User related operations

    //create
    User saveUser(User user);

    //Fetch all users
    List<User> fetchAllUser();

    //Fetch particular user
    User getUser(String userId);
}
