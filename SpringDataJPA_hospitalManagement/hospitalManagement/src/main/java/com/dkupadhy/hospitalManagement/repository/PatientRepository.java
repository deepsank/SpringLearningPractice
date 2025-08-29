package com.dkupadhy.hospitalManagement.repository;

import com.dkupadhy.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.dkupadhy.hospitalManagement.entity.Patient;
import com.dkupadhy.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);

//    Patient findByBirthDateOrEmail(LocalDate date, String email);
//    Optional<Patient> findByBirthDateOrEmail(LocalDate date, String email);
    List<Patient> findByBirthDateOrEmail(LocalDate date, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContaining(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup= ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select  p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birth_Date);

//    @Query("select p.bloodGroup, Count(p) from Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();
    @Query("select new com.dkupadhy.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, Count(p))" +
            " from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

//    @Query(value = "select * from patient", nativeQuery = true)
//    List<Patient> findAllPatients();
    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);


//    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();

}
