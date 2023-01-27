package com.jdc.leave.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.leave.model.dto.input.RegistrationForm;
import com.jdc.leave.model.dto.output.RegistrationDetailsVO;
import com.jdc.leave.model.dto.output.RegistrationListVO;

@Service
public class RegistrationService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;
	
	private BeanPropertyRowMapper<RegistrationListVO> rowmapper;
	@Autowired
	private ClassService classService;
	@Autowired
	private StudentService studentService;
	
	private static final String SELECT_SQL_BY_STUDENT ="""
	select r.classes_id classId,ta.id teacherId,ta.name teacher,c.start_date startDate,sa.id studentId,sa.name student,s.phone studentPhone,r.registration_date registrationDate,c.description classInfo
	from registration r join classes c on r.classes_id = c.id
	join teacher t on c.teacher_id = t.id
	join account ta on t.id = ta.id
	join student s on r.student_id = s.id
	join account sa on s.id = sa.id
	where r.student_id = :studentId
	""";
	
	private static final String GROUP_BY = """
			group by r.classes_id,ta.id,ta.name,c.start_date,sa.id,sa.name,c.description,s.phone,r.registration_date
			""";
	
	public RegistrationService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		rowmapper = new BeanPropertyRowMapper<>(RegistrationListVO.class);
		
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("registration");
		
	}
	
	public List<RegistrationListVO> searchByClassId(int id){
		var sql = """
				select r.classes_id classId,ta.id teacherId,ta.name teacher,c.start_date startDate,c.description classInfo,sa.id studentId,sa.name student,s.phone studentPhone,r.registration_date registrationDate
				from registration r join classes c on r.classes_id = c.id
				join teacher t on c.teacher_id = t.id
				join account ta on t.id = ta.id
				join student s on r.student_id = s.id
				join account sa on s.id = sa.id
				where r.classes_id = :classId
				""";
		return template.query(sql, Map.of("classId",id),rowmapper);
	}
	
	public List<RegistrationListVO> searchByStudentId(int id){
		return template.query(SELECT_SQL_BY_STUDENT, Map.of("studentId",id),rowmapper);
	}
	
	@Transactional
	public void save(RegistrationForm form) {
		
		var id = form.getStudentId();
		if( id > 0) {
			 update(form);
		}
		 insert(form);
	}
	
	private void insert(RegistrationForm form) {
		
		var studentId = studentService.findStudentByEmail(form.getEmail());
		if(studentId == 0) {
			studentId = studentService.createStudent(form);
		}
		form.setStudentId(studentId);
		
		if(form.getRegistrationDate() == null) {
			form.setRegistrationDate(LocalDate.now());
		}
		
		insert.execute(Map.of(
				"classes_id",form.getClassId(),
				"student_id",form.getStudentId(),
				"registration_date",form.getRegistrationDate()
				));
	}

	private void update(RegistrationForm form) {
		
		template.update("update registration set student_id = :studentId,registration_date = :regDate where classes_id = :classId",
				Map.of(
				
				"studentId",form.getStudentId(),
				"regdate",form.getRegistrationDate(),
				"classId",form.getClassId()
				));
		template.update("update student set phone = :phone, education = :education where id = :id",
				Map.of("phone",form.getPhone(),
				"education",form.getEducation(),
				"id",form.getStudentId()));
	}

	public RegistrationDetailsVO findDetailById(int classId,int stuId) {
		
		var result = new RegistrationDetailsVO();
		
		var sql = "select registration_date from registration where classes_id = :classId and student_id = :stuId";
		
		var registDate = template.queryForObject(sql, Map.of("classId",classId,"stuId",stuId), Date.class);
		
		result.setRegistrationDate(registDate.toLocalDate());
		
		result.setClassInfo(classService.findInfoById(classId));
		
		result.setStudentInfo(studentService.findInfoById(stuId));
		
		return result;
	}
	
	public RegistrationForm getFormById(int classId,int studentId) {
		return template.queryForObject("select * from registration where classes_id = :classId and student_id =:studentId",
				Map.of("classId",classId,"studentId",studentId), new BeanPropertyRowMapper<>(RegistrationForm.class));
	}

}
