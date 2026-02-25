package com.deepak.microservices.Userservice.external.services;

import com.deepak.microservices.Userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HotelService")
public interface HotelService {

    @GetMapping("hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
