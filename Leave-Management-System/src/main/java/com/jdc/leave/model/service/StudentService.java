package com.jdc.leave.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leave.model.dto.input.RegistrationForm;
import com.jdc.leave.model.dto.output.StudentDetailVo;
import com.jdc.leave.model.dto.output.StudentListVO;

@Service
public class StudentService {
	
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;
	private SimpleJdbcInsert accInsert;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RegistrationService regService;
	private BeanPropertyRowMapper<StudentListVO> rowmapper;
	private static final String SELECT_SQL = """
			select a.id studentId,a.name,s.phone,a.email,s.education,count(r.classes_id) classCount 
			from account a join student s on a.id = s.id join registration r on s.id=r.student_id 
			""";
	private static final String GROUPBY_SQL = "group by a.id,a.name,s.phone,a.email,s.education";
	
	public StudentService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		rowmapper = new BeanPropertyRowMapper<>(StudentListVO.class);
		
		accInsert = new SimpleJdbcInsert(dataSource);
		accInsert.setTableName("account");
		accInsert.setGeneratedKeyName("id");
		accInsert.setColumnNames(List.of(
				"name","role","email","password"));
		
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("student");
		
		
	}
	
	public List<StudentListVO> search(Optional<String> name,Optional<String> phone,Optional<String> email){
		
		var data = new StringBuffer();
		
		var param = new HashMap<String,Object>();
		
		data.append(name.filter(StringUtils::hasLength)
				.map(a->{
				param.put("name", a.toLowerCase().concat("%"));
				return "and lower(a.name) Like :name ";
				}).orElse(""));
		data.append(phone.filter(StringUtils::hasLength)
				.map(a->{
				param.put("phone", a.concat("%"));
				return "and s.phone Like :phone ";
				}).orElse(""));
		data.append(email.filter(StringUtils::hasLength)
				.map(a->{
				param.put("email", a.concat("%"));
				return "and a.email Like :email";
				}).orElse(""));
		
		var sql = "%s where 1=1 %s %s".formatted(SELECT_SQL,data.toString(),GROUPBY_SQL);
		return template.query(sql, param, rowmapper);
	}
	
	public StudentListVO findInfoById(int id) {
		return template.queryForObject("select * from student s join account a on a.id=s.id where s.id =:id",Map.of("id",id),rowmapper);
	}
	
	public Integer findStudentByEmail(String email) {
		
		return template.queryForList("select a.id from account a join student s on s.id=a.id where a.email = :email",
				Map.of("email",email), Integer.class).stream().findFirst().orElse(0);
		
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer createStudent(RegistrationForm form) {
		var generatedId = accInsert.executeAndReturnKey(Map.of(
				"name",form.getStudentName(),
				"role","Student",
				"email",form.getEmail(),
				"password",passwordEncoder.encode(form.getPhone())
				));
		
		insert.execute(Map.of("id",generatedId.intValue(),"phone",form.getPhone(),"education",form.getEducation()));
		return generatedId.intValue();
	}

	public StudentDetailVo findStudentByLoginId(String email) {
		var result = new StudentDetailVo();
		
		var studentId = findStudentByEmail(email);
		
		result.setStudent(findInfoById(studentId));
		result.setRegistrations(regService.searchByStudentId(studentId));
		return result;
	}

}
