package com.example.LearnRESTAPIs.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.LearnRESTAPIs.dto.AddStudentRequestDto;
import com.example.LearnRESTAPIs.dto.PatchStudentRequestDto;
import com.example.LearnRESTAPIs.dto.StudentDto;
import com.example.LearnRESTAPIs.dto.UpdateStudentRequestDto;
import com.example.LearnRESTAPIs.entity.Student;
import com.example.LearnRESTAPIs.repository.StudentRepository;
import com.example.LearnRESTAPIs.service.StudentService;

/**
 * This class contains actual business logic.
 * It talks to the database via repository.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor Injection (best practice)
     * Spring automatically injects dependencies here
     */
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all students from DB and convert them into DTOs
     */
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    /**
     * Get a single student by ID
     */
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found " + id));

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updateStudentById(Long id, UpdateStudentRequestDto updateStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found " + id));

        student.setName(updateStudentRequestDto.getName());
        student.setEmail(updateStudentRequestDto.getEmail());
        student.setAge(updateStudentRequestDto.getAge());
        student.setMobileNo(updateStudentRequestDto.getMobileNo());

        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public StudentDto patchStudentById(Long id, PatchStudentRequestDto patchStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found " + id));

        if (patchStudentRequestDto.getName() != null) {
            student.setName(patchStudentRequestDto.getName());
        }

        if (patchStudentRequestDto.getEmail() != null) {
            student.setEmail(patchStudentRequestDto.getEmail());
        }
        
        if (patchStudentRequestDto.getAge() != null) {
            student.setAge(patchStudentRequestDto.getAge());
        }

        if (patchStudentRequestDto.getMobileNo() != null) {
            student.setMobileNo(patchStudentRequestDto.getMobileNo());
        }

        Student patchedStudent = studentRepository.save(student);
        return modelMapper.map(patchedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist by id " + id);
        }
        studentRepository.deleteById(id);
    }
    
    @Override
    public Page<StudentDto> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(student -> modelMapper.map(student, StudentDto.class));
    }
    
    @Override
    public Page<StudentDto> getAllStudents(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(student -> modelMapper.map(student, StudentDto.class));
    }
    
    @Override
    public List<StudentDto> searchStudents(String name) {

        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }
    
    //---------------------------
}