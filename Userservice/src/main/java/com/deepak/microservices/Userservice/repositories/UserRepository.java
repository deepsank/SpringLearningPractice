package com.deepak.microservices.Userservice.repositories;

import com.deepak.microservices.Userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
