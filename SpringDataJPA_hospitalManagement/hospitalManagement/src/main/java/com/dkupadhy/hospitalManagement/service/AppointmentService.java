package com.dkupadhy.hospitalManagement.service;


import com.dkupadhy.hospitalManagement.entity.Appointment;
import com.dkupadhy.hospitalManagement.entity.Doctor;
import com.dkupadhy.hospitalManagement.entity.Patient;
import com.dkupadhy.hospitalManagement.repository.AppointmentRepository;
import com.dkupadhy.hospitalManagement.repository.DoctorRepository;
import com.dkupadhy.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have been there already");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // Just to maintain bi-directional consistency

        appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);   //Appointment made DIRTY (dirty state) --- this will auto,automatically call the update

        doctor.getAppointments().add(appointment);  //Just for maintaining bi-directional consistency

        return appointment;
    }
}
