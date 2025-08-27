package com.dkupadhy.learningSpringBootApp.RESTAPIs.service;

import com.dkupadhy.learningSpringBootApp.RESTAPIs.dto.AddStudentRequestDto;
import com.dkupadhy.learningSpringBootApp.RESTAPIs.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long studentId);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartiaslStudent(Long id, Map<String, Object> updates);
}
