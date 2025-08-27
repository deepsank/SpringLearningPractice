package com.dkupadhy.learningSpringBootApp.RESTAPIs.service.impl;

import com.dkupadhy.learningSpringBootApp.RESTAPIs.dto.AddStudentRequestDto;
import com.dkupadhy.learningSpringBootApp.RESTAPIs.dto.StudentDto;
import com.dkupadhy.learningSpringBootApp.RESTAPIs.entity.Student;
import com.dkupadhy.learningSpringBootApp.RESTAPIs.repository.StudentRepository;
import com.dkupadhy.learningSpringBootApp.RESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;          // final means required at initialization
    private final ModelMapper modelMapper;          //ModelMapper is to make object mapping easy,
                                        // by automatically determining how one object model maps to another
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students
                .stream()
                .map(student -> new StudentDto(student.getId(),student.getName(),student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+studentId));

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist by Id: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));
        modelMapper.map(addStudentRequestDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updatePartiaslStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));

        updates.forEach((field ,value) -> {
                    switch (field) {
                        case "name":
                            student.setName((String) value);
                            break;
                        case "email":
                            student.setEmail((String) value);
                            break;
                        default:
                            throw new IllegalArgumentException("Field is not supported");
                    }
                }
                );
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent,StudentDto.class);
    }

}
