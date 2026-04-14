package com.example.LearnRESTAPIs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for sending student data
 * Lombok generates:
 * - getters
 * - setters
 * - toString()
 * - equals() & hashCode()
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private long id;
    private String name;
    private String email;
	private String mobileNo;
	private int age;
}