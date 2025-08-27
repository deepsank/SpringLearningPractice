package com.dkupadhy.learningSpringBootApp.RESTAPIs.repository;

import com.dkupadhy.learningSpringBootApp.RESTAPIs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository         //Optional as its just an interface
public interface StudentRepository extends JpaRepository<Student, Long> {
}
