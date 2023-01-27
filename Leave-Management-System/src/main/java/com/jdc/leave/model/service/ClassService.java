package com.jdc.leave.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leave.model.dto.input.ClassForm;
import com.jdc.leave.model.dto.output.ClassDetailsVO;
import com.jdc.leave.model.dto.output.ClassListVO;

@Service
public class ClassService {
	
	private static final String SELECT_PROJECTION = """
			select c.id id,t.id teacherId,a.name teacherName,t.phone teacherPhone,c.start_date startDate,
				c.months,c.description,count(r.classes_id and r.student_id) studentCount
				from classes c join teacher t on t.id = c.teacher_id
				join account a on a.id = t.id
				left join registration r on c.id = r.classes_id where 1 = 1 
			""";
	private static final Object SELECT_GROUPBY = """
			group by c.id,t.id,a.name,t.phone,c.start_date,c.months,c.description
			""";
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;
	@Autowired
	private RegistrationService regService;
	@Autowired
	private LeaveService leaService;
	
	public ClassService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("classes");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("teacher_id","start_date","months","description"));
	}
	
	public List<ClassListVO> search(Optional<String> teacherName,Optional<LocalDate> from,Optional<LocalDate> to){
		
		var stringData = new StringBuffer(SELECT_PROJECTION);
		
		var params = new HashMap<String,Object>();
		stringData.append(teacherName.filter(StringUtils::hasText).map(a -> {
			params.put("teacherName", a.toLowerCase().concat("%"));
			return "and lower(a.name) Like :teacherName ";
		}).orElse(""));
		
		stringData.append(from.map(a -> {
			params.put("from", Date.valueOf(a));
			return "and c.start_date >= :from ";
		}).orElse(""));
		
		stringData.append(to.map(a -> {
			params.put("to", Date.valueOf(a));
			return "and c.start_date <= :to ";
		}).orElse(""));
		
		stringData.append(SELECT_GROUPBY);
		
		return template.query(stringData.toString(), params, new BeanPropertyRowMapper<>(ClassListVO.class));
	}
	@Transactional
	public int save(ClassForm form) {
		if(form.getId() == 0) {
			return insert(form);
		}
		return update(form);
	}
	
	private int update(ClassForm form) {
		template.update("""
				update classes set teacher_id = :teacher,start_date =:date,months=:months,description = :description where id=:id
				""",Map.of(
						"teacher",form.getTeacher(),
						"date",Date.valueOf(form.getStart()),
						"months",form.getMonths(),
						"description",form.getDescription(),
						"id",form.getId()));
		return form.getId();
	}

	private int insert(ClassForm form) {

		var generatedKey = insert.executeAndReturnKey(Map.of(
				"teacher_id",form.getTeacher(),
				"start_date",Date.valueOf(form.getStart()),
				"months",form.getMonths(),
				"description",form.getDescription()
				));
		return generatedKey.intValue();
		
	}
	
	public ClassForm findById(int id) {
		
		return template.queryForObject("select * from classes where id = :id", Map.of("id",id), new ClassFormRowmapper());
	}
	
	public ClassListVO findInfoById(int id) {
		var sql = """
				select c.id id,t.id teacherId,a.name teacherName,t.phone teacherPhone,
				c.start_date startDate,c.months,c.description,count(r.classes_id and r.student_id) studentCount 
				from classes c join teacher t on t.id = c.teacher_id join account a on a.id = t.id 
				left join registration r on c.id = r.classes_id where c.id=:id
				""";
		return template.queryForObject(sql, Map.of("id",id), new BeanPropertyRowMapper<>(ClassListVO.class));
	}

	public ClassDetailsVO findDetailById(int id) {
		
		var result = new ClassDetailsVO();
		
		//class info
		var sql = "%s and c.id = :id %s".formatted(SELECT_PROJECTION,SELECT_GROUPBY);
		var classListVo = template.queryForObject(sql, Map.of("id",id), new ClassListVORowmapper());
		
		result.setClassInfo(classListVo);
		
		//registration info
		result.setRegistrations(regService.searchByClassId(id));
		
		//leaves info
		result.setLeaves(leaService.search(Optional.of(id), Optional.empty(), Optional.empty()));
		return result;
	}

}
