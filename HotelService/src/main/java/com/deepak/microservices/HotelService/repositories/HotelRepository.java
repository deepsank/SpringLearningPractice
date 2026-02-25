package com.deepak.microservices.HotelService.repositories;

import com.deepak.microservices.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
