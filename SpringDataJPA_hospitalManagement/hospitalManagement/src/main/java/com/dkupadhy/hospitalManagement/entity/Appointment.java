package com.dkupadhy.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 5000)
    private String reason;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "patient_id", nullable = false)  // patient is required and hence not nullable
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)          // By Default --- FETCHTYPE.EAGER
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Doctor doctor;

}
