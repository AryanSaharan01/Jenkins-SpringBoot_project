package com.example.LearnRESTAPIs.dto;

import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.NotBlank;

public class AddStudentRequestDto {

	@NotBlank(message = "Name cannot be empty")
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email cannot be empty")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	private String mobileNo;

	@Min(value = 1, message = "Age must be greater than 0")
	@Max(value = 100, message = "Age must be less than 100")
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
