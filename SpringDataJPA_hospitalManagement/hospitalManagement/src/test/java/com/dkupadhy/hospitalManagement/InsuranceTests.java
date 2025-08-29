package com.dkupadhy.hospitalManagement;


import com.dkupadhy.hospitalManagement.entity.Appointment;
import com.dkupadhy.hospitalManagement.entity.Insurance;
import com.dkupadhy.hospitalManagement.entity.Patient;
import com.dkupadhy.hospitalManagement.service.AppointmentService;
import com.dkupadhy.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
//        Insurance insurance = new Insurance();   //Used @Builder OF LOMBOK hence we can ignore this
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_2345")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.disAssociateInsuranceFromPatient(patient.getId());

        System.out.println(newPatient);

    }

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,2,14,0,0))
                .reason("Cancer")
                .build();

        var newAppointment = appointmentService.createAppointment(appointment, 1L, 2L);

        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);

        System.out.println(updatedAppointment);
    }
}
