package com.jdc.leave.model.dto.input;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class LeaveForm {
	
	private int classId;
	
	private int studentId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate applyDate;
	@NotNull(message = "please enter leave strat day")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@Min(value = 1,message = "Please enter leave days.")
	private int days;
	@NotEmpty(message = "Please enter reason.")
	private String reason;

	public LeaveForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveForm(int classId, int studentId) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		
	}

	public LeaveForm(int classId, int studentId, LocalDate applyDate, LocalDate startDate,int days,String reason) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		this.applyDate = applyDate;
		this.startDate = startDate;
		this.days = days;
		this.reason = reason;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int student) {
		this.studentId = student;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Map<String, Object> leavesInsertParam(){
		return Map.of("apply_date",Date.valueOf(applyDate),
				"classes_id",classId,
				"student_id",studentId,
				"start_date",Date.valueOf(startDate),
				"days",days,
				"reason",reason
				);
	}
	
	public List<Map<String, Object>> leavesDayInsertParam(){
		return IntStream.iterate(0, a->a+1).limit(days)
				.mapToObj(a->startDate.plusDays(a))
				.map(this::leavesDayInsertParam)
				.toList();
	}
	
	public Map<String, Object> leavesDayInsertParam(LocalDate date){
		return Map.of("leave_date",date,
					"leaves_apply_date",Date.valueOf(applyDate),
					"leaves_classes_id",classId,
					"leaves_student_id",studentId);
	}
	
	

}
