package com.jdc.leave.model.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leave.model.dto.input.TeacherForm;
import com.jdc.leave.model.dto.output.IdWithName;
import com.jdc.leave.model.dto.output.TeacherListVO;

@Service
public class TeacherService {
	
	
	private static final String SELECT_PROJECTION = """
			select t.id,a.name,t.phone,a.email,t.assign_date assignDate,count(c.id) classCount from teacher t join account a on a.id=t.id left join classes c on c.teacher_id=t.id
			""";
	private static final String SELECT_GROUP_BY = """
			group by t.id,a.name,t.phone,a.email,t.assign_date
			""";
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert accountInsert;
	private SimpleJdbcInsert teacherInsert;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private RowMapper<TeacherListVO> rowMapper;
	
	
	public TeacherService(DataSource dataSource) {
		
		rowMapper = new BeanPropertyRowMapper<>(TeacherListVO.class);
		
		template = new NamedParameterJdbcTemplate(dataSource);
		
		accountInsert = new SimpleJdbcInsert(dataSource);
		accountInsert.setTableName("account");
		accountInsert.setGeneratedKeyName("id");
		accountInsert.setColumnNames(List.of(
				"name","role","email","password"));
		
		teacherInsert = new SimpleJdbcInsert(dataSource);
		teacherInsert.setTableName("teacher");
				
	}
	
	public List<TeacherListVO> search(Optional<String> name,Optional<String> phone,Optional<String> email){
		
		var where = new StringBuffer();
		
		var params = new HashMap<String,Object>();
		
		//dynamic query
		
		where.append(email.filter(StringUtils::hasLength).map(a -> {
			params.put("email", a.concat("%"));
			return "and a.email Like :email ";
		}).orElse(""));
		
		where.append(phone.filter(StringUtils::hasLength).map(a -> {
			params.put("phone", a.concat("%"));
			return "and t.phone Like :phone ";
		}).orElse(""));
		
		where.append(name.filter(StringUtils::hasLength).map(a -> {
			params.put("name", a.toLowerCase().concat("%"));
			return "and lower(a.name) Like :name";
		}).orElse(""));
		
		var sql = "%s where 1=1 %s %s".formatted(SELECT_PROJECTION,where.toString(),SELECT_GROUP_BY);
		
		return template.query(sql, params, rowMapper);
	}
	
	public TeacherListVO findById(int id) {
		var sql = "%s where %s %s".formatted(SELECT_PROJECTION,"t.id=:id",SELECT_GROUP_BY);
		return template.queryForObject(sql, Map.of("id",id), rowMapper);
	}
	
	@Transactional
	public int save(TeacherForm form) {
		if(form.getId() == 0) {
			return insert(form);
		}
		
		return update(form);
		
	}
	
	
	
	private int update(TeacherForm form) {
		// update account table
		template.update("update account set name=:name where id=:id", Map.of(
				"name",form.getName(),
				"id",form.getId()));
		
		// update account table
		template.update("update teacher set phone=:phone,assignDate=:assign where id=:id", Map.of(
				"phone",form.getPhone(),
				"assign",Date.valueOf(form.getAssignDate()),
				"id",form.getId()));
		return form.getId();
	}

	private int insert(TeacherForm form) {
		
		//insert into account
		var generatedId = accountInsert.executeAndReturnKey(Map.of(
				"name",form.getName(),
				"role","Teacher",
				"email",form.getEmail(),
				"password",passwordEncoder.encode(form.getPhone())
				));
		
		//insert into teacher
		teacherInsert.execute(Map.of(
				"id",generatedId.intValue(),
				"phone",form.getPhone(),
				"assign_date",Date.valueOf(form.getAssignDate())
				));
		
		return generatedId.intValue();
	}

	public List<IdWithName> getAvailableTeachers() {
		// TODO Auto-generated method stub
		return template.query("select t.id,a.name from teacher t join account a on t.id=a.id where a.deleted = :del",
				Map.of("del",false),
				new BeanPropertyRowMapper<>(IdWithName.class));
				}


}
