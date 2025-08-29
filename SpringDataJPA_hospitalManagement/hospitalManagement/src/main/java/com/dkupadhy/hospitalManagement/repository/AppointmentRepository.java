package com.dkupadhy.hospitalManagement.repository;

import com.dkupadhy.hospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}