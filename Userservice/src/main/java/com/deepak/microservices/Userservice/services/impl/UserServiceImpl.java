package com.deepak.microservices.Userservice.services.impl;

import com.deepak.microservices.Userservice.entities.Hotel;
import com.deepak.microservices.Userservice.entities.Rating;
import com.deepak.microservices.Userservice.entities.User;
import com.deepak.microservices.Userservice.exceptions.ResourceNotFoundException;
import com.deepak.microservices.Userservice.external.services.HotelService;
import com.deepak.microservices.Userservice.repositories.UserRepository;
import com.deepak.microservices.Userservice.services.UserService;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

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
//        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
        Rating[] ratings = restTemplate.getForObject("http://RatingService/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> listOfRatings = Arrays.stream(ratings).toList();
        listOfRatings.stream().map(rating->{
            //api call to get hotel from hotel service
            //http://localhost:8082/hotels/19d99f95-f000-446d-8334-8566a0782019
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HotelService/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("Resp status code: {}",forEntity.getStatusCode());
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;

            //set the hotel details to ratings
            //return rating
        }).collect(Collectors.toList());
        logger.info("{} ",listOfRatings);
        user.setRatings(listOfRatings);
        return user;
    }
}
