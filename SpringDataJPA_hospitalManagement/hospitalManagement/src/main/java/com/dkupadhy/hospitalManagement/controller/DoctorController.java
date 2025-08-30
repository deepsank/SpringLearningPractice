package com.dkupadhy.hospitalManagement.controller;

import com.dkupadhy.hospitalManagement.dto.AppointmentResponseDto;
import com.dkupadhy.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor() {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(1L));
    }

}
