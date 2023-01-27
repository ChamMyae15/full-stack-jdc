package com.jdc.leave.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class RegistrationForm {
	
	
	private int classId;
	
	private int studentId;
	
	@NotEmpty(message = "Please enter student name.")
	private String studentName;
	
	@NotEmpty(message = "Please enter student email address.")
	private String email;
	
	@NotEmpty(message = "Please enter studnet phone number.")
	private String phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate;
	@NotEmpty(message = "Please enter studnet last education.")
	private String education;
	
	public RegistrationForm(int classId, int studentId, String studentName, String email, String phone,
			LocalDate registrationDate, String education) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.email = email;
		this.phone = phone;
		this.registrationDate = registrationDate;
		this.education = education;
	}



	public LocalDate getRegistrationDate() {
		return registrationDate;
	}



	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public RegistrationForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}



	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
