package com.example.LearnRESTAPIs.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.LearnRESTAPIs.dto.AddStudentRequestDto;
import com.example.LearnRESTAPIs.dto.PatchStudentRequestDto;
import com.example.LearnRESTAPIs.dto.StudentDto;
import com.example.LearnRESTAPIs.dto.UpdateStudentRequestDto;

/**
 * This is the contract.
 * Whatever is declared here MUST be implemented.
 */
public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    StudentDto updateStudentById(Long id, UpdateStudentRequestDto updateStudentRequestDto);

    StudentDto patchStudentById(Long id, PatchStudentRequestDto patchStudentRequestDto);

    void deleteStudentById(Long id);
    
    Page<StudentDto> getAllStudents(int page, int size);
    
    Page<StudentDto> getAllStudents(int page, int size, String sortBy, String direction);

    List<StudentDto> searchStudents(String name);
}