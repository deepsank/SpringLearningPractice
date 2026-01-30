package com.deepak.microservices.Userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "Name", length = 50)
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Info")
    private String info;

    @Transient  //an annotation to mark a field that shouldn’t be persisted in a database when using frameworks like JPA/Hibernate.
    private List<Rating> ratings = new ArrayList<>();
}
