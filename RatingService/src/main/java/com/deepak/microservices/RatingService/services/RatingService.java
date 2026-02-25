package com.deepak.microservices.RatingService.services;

import com.deepak.microservices.RatingService.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    //create
    Rating addRating(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
