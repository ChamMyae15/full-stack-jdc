package com.jdc.leave.test.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leave.model.dto.input.TeacherForm;
import com.jdc.leave.model.service.TeacherService;

@SpringJUnitConfig(locations = "/root-config.xml")
@Sql(scripts = "/sql/truncate.sql")
public class TeacherServiceTest {
	@Autowired
	TeacherService service;
	
	@ParameterizedTest
	@CsvSource(value = "0,Maung Maung,0978990782,mg.maung@gmail.com,2021-08-21")
	@Sql(scripts = "/sql/truncate.sql")
	void saveInsertSucess(int id, String name, String phone, String email, LocalDate assignDate) {
		var form = new TeacherForm(id, name, phone, email, assignDate);
		var result = service.save(form);
		
		assertEquals(1, result);
		
	}
	@ParameterizedTest
	@Sql(scripts = {
			"/sql/truncate.sql",	
			"/sql/teacher.sql"
	})
	@CsvSource(value = {"1,Ma Ma,09876543451,mama@gmail.com,2022-12-01,2",
						"2,Zaw Zaw,09789754310,zaw@gmail.com,2022-10-26,1",
						"3,Ko Ko,0998764362,ko@gmail.com,2022-03-19,0"
			
	})
	void findByIdTest(int id,String name,String phone,String email,LocalDate assignDate,int classCount) {
		
		var result = service.findById(id);
		
		assertEquals(name, result.getName());
		assertEquals(phone, result.getPhone());
		assertEquals(email, result.getEmail());
		assertEquals(assignDate, result.getAssignDate());
		assertEquals(classCount, result.getClassCount());
		
	}
	@ParameterizedTest
	@Sql(scripts = {
			"/sql/truncate.sql",	
			"/sql/teacher.sql"
	})
	@CsvSource(value = {
			",,,3",
			"ma,,,1",
			"myo,,,0",
			"ko,0998764362,,1",
			"ko,0998764355,,1",
			",097897,,0",
			",,zaw,1",
			",,ag,0"
			
			
	})
	
	void searchTest(String name,String phone,String email,int count) {
		var result = service.search(Optional.ofNullable(name), Optional.ofNullable(phone), Optional.ofNullable(email));
		
		assertEquals(count,result.size());
	}

}
