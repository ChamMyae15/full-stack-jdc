package com.jdc.leave.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentForm {
	
	
	private int id;
	@NotEmpty(message = "Please enter student name.")
	private String name;
	@NotEmpty(message = "Please enter student phone.")
	private String phone;
	@NotEmpty(message = "Please enter student email.")
	private String email;
	@NotEmpty(message = "Please enter student education.")
	private String education;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
}
