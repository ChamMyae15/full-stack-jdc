package com.jdc.leave.model.dto.output;

import java.util.List;

public class StudentDetailVo {
	
	private StudentListVO student;
	private List<RegistrationListVO> registrations;
	
	public StudentListVO getStudent() {
		return student;
	}
	public void setStudent(StudentListVO student) {
		this.student = student;
	}
	public List<RegistrationListVO> getRegistrations() {
		return registrations;
	}
	public void setRegistrations(List<RegistrationListVO> registrations) {
		this.registrations = registrations;
	}

}
