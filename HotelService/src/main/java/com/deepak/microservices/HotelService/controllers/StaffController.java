package com.deepak.microservices.HotelService.controllers;

import com.deepak.microservices.HotelService.entities.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    ResponseEntity<List<String>> fetchStaff(){
//        List<String> hotels = new ArrayList<>();
//        hotels.add("Ram");
//        hotels.add("Shyam");
//        hotels.add("Krishna");
//        hotels.add("Sita");
        List<String> hotels = Arrays.asList("Ram", "Shyam", "Sita", "Krishna");
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
