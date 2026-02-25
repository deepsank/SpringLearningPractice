package com.deepak.microservices.Userservice.controllers;

import com.deepak.microservices.Userservice.entities.User;
import com.deepak.microservices.Userservice.services.UserService;
import com.deepak.microservices.Userservice.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //Fetch particular user
    int retryCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelBreakerFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelBreakerFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelRateLimiterFallback")
    public ResponseEntity<User> fetchUser(@PathVariable String userId){
        logger.info("Get Single User Handler: UserController");

//        logger.info("Retry count : {}", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Creating fallback method for Rate Limiter

    public ResponseEntity<User> ratingHotelRateLimiterFallback(String userId, Exception ex){
        logger.info("Fallback is executed as userRateLimiter limit reached : {}",ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .userId(userId)
                .name("dummy")
                .info("This is dummy response aas userRateLimiter limit reached.")
                .build();

        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
    }

    // Creating fallback method for Cicuit breaker

    public ResponseEntity<User> ratingHotelBreakerFallback(String userId, Exception ex){
        logger.info("Fallback is executed as hotel or rating service is down : {}",ex.getMessage());
         User user = User.builder()
                .email("dummy@gmail.com")
                .userId(userId)
                .name("dummy")
                .info("This is dummy response as one of the internal service is down.")
                .build();

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //Fetch all users
    @GetMapping("/")
    public ResponseEntity<List<User>> fetchAllUsers(){
         List<User> allUsers = userService.fetchAllUser();
         return ResponseEntity.ok(allUsers);
    }
}
