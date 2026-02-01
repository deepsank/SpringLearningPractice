package com.deepak.microservices.HotelService.services;

import com.deepak.microservices.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
