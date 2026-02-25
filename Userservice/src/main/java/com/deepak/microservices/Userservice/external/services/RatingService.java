package com.deepak.microservices.Userservice.external.services;

import com.deepak.microservices.Userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@FeignClient( name = "RatingService")
public interface RatingService {

    //Get

    //Post
    @PostMapping("/ratings")
//    Rating saveRating(Map<String,Object> values); // This can be user if we don't have our Request class
    ResponseEntity<Rating> saveRating(Rating rating);

//    //Put
//    @PutMapping("ratings/{ratingId}")
//    Rating updateRating(@PathVariable("ratingId")String ratingId, Rating rating);
//
//    //Delete
//    @DeleteMapping("ratings/{ratingId}")
//    void deleteRating(@PathVariable("ratingId")String ratingId);
}
