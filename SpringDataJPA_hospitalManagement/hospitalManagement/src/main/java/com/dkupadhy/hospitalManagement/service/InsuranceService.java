package com.dkupadhy.hospitalManagement.service;


import com.dkupadhy.hospitalManagement.entity.Insurance;
import com.dkupadhy.hospitalManagement.entity.Patient;
import com.dkupadhy.hospitalManagement.repository.InsuranceRepository;
import com.dkupadhy.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id:- "+patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bi-directional consistency maintainence

        return patient;

    }


    @Transactional
    public Patient disAssociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id:- " + patientId));

        patient.setInsurance(null);
        return patient;
    }

    //HW
    //Create 3 appointment for a patient and then delete Patient
}
