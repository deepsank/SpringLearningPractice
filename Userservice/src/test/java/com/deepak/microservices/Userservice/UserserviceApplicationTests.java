package com.deepak.microservices.Userservice;

import com.deepak.microservices.Userservice.entities.Rating;
import com.deepak.microservices.Userservice.external.services.RatingService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void saveRating(){
		Rating rating = Rating.builder()
				.rating(9)
				.userId("")
				.hotelId("")
				.feedback("This is created using FeignClient.")
				.build();
		ResponseEntity<Rating> savedRating = ratingService.saveRating(rating);

		System.out.println("New rating created!!! :-- "+savedRating);
	}

}
