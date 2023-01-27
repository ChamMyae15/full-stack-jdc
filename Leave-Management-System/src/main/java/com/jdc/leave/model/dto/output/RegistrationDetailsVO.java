package com.jdc.leave.model.dto.output;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class RegistrationDetailsVO {
	
	private ClassListVO classInfo;
	
	private StudentListVO studentInfo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate;

	public ClassListVO getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassListVO classInfo) {
		this.classInfo = classInfo;
	}

	public StudentListVO getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentListVO studentInfo) {
		this.studentInfo = studentInfo;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	

	

}
