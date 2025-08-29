package com.dkupadhy.hospitalManagement;

import com.dkupadhy.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.dkupadhy.hospitalManagement.entity.Patient;
import com.dkupadhy.hospitalManagement.entity.type.BloodGroupType;
import com.dkupadhy.hospitalManagement.repository.PatientRepository;
import com.dkupadhy.hospitalManagement.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepositor(){
//        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);

    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1L);

//        Patient patient = patientRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException("Patient not found " +
//                "with id: 1"));

//        Patient patient = patientRepository.findByName("Bruce Wayne");
//        System.out.println(patient);

//        List<Patient> patientList = patientRepository
//                .findByBirthDateOrEmail(LocalDate.of(1990,05,15),"shaktiman@gmail.com");

//        List<Patient> patientList = patientRepository
//                .findByNameContaining("an");
//        List<Patient> patientList = patientRepository
//                .findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1985,04,30));
//        for (Patient patient : patientList){
//            System.out.println(patient);
//        }
//        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for (Object[] objects : bloodGroupList){
//            System.out.println(objects[0]+ " "+ objects[1]);
//        }

//        List<Patient> patientList = patientRepository.findAllPatients();
//        for (Patient patient : patientList){
//            System.out.println(patient);
//        }

//        int rowsUpdated = patientRepository.updateNameWithId("Deepak Upadhyay", 1L);
//        System.out.println(rowsUpdated);

//        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for (BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList){
//            System.out.println(bloodGroupCountResponse);
//        }

        Page<Patient> patientList = patientRepository
                .findAllPatients(PageRequest.of(1,2, Sort.by("name")));

        for(Patient patient: patientList){
            System.out.println(patient);
        }


    }
}
