package com.deepak.microservices.Userservice.controllers;

import com.deepak.microservices.Userservice.entities.User;
import com.deepak.microservices.Userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //Fetch particular user
    @GetMapping("/{userId}")
    public ResponseEntity<User> fetchUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //Fetch all users
    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers(){
         List<User> allUsers = userService.fetchAllUser();
         return ResponseEntity.ok(allUsers);
    }
}
