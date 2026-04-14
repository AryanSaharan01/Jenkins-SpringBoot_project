package com.example.LearnRESTAPIs.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnRESTAPIs.dto.AddStudentRequestDto;
import com.example.LearnRESTAPIs.dto.PatchStudentRequestDto;
import com.example.LearnRESTAPIs.dto.StudentDto;
import com.example.LearnRESTAPIs.dto.UpdateStudentRequestDto;
import com.example.LearnRESTAPIs.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudent() {
//		return studentService.getAllStudents();
//		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("students/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
//		return studentService.getStudentById(id);
		return ResponseEntity.ok(studentService.getStudentById(id));

	}

	@GetMapping("/students/paginated")
	public ResponseEntity<Page<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(studentService.getAllStudents(page, size));
	}

	@GetMapping("/students/sorted")
	public ResponseEntity<Page<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy, direction));
	}

	@GetMapping("/students/search")
	public ResponseEntity<List<StudentDto>> searchStudents(@RequestParam String name) {
		return ResponseEntity.ok(studentService.searchStudents(name));
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDto> createNewStudent(@Valid @RequestBody AddStudentRequestDto addStudentRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
	}


	@DeleteMapping("students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<StudentDto> updateStudentById(@Valid @PathVariable Long id,
			@RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
		return ResponseEntity.ok(studentService.updateStudentById(id, updateStudentRequestDto));
	}

	@PatchMapping("/students/{id}")
	public ResponseEntity<StudentDto> patchStudentById(@PathVariable @Valid Long id,
			@RequestBody PatchStudentRequestDto patchStudentRequestDto) {
		return ResponseEntity.ok(studentService.patchStudentById(id, patchStudentRequestDto));
	}

}
