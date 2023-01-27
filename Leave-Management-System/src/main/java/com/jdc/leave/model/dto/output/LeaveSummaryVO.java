package com.jdc.leave.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LeaveSummaryVO {
	
	private int classId;
	
	private String teacher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	private long students;
	
	private long leaves;
	
	private String detail;
	
	public LeaveSummaryVO(ClassListVO vo) {
		super();
		this.classId = vo.getId();
		this.teacher = vo.getTeacherName();
		this.startDate = vo.getStartDate();
		this.students = vo.getStudentCount();
		this.detail = vo.getDescription();
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public long getStudents() {
		return students;
	}

	public void setStudents(long students) {
		this.students = students;
	}

	public long getLeaves() {
		return leaves;
	}

	public void setLeaves(long leaves) {
		this.leaves = leaves;
	}

	public LeaveSummaryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
