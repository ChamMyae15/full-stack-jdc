package com.jdc.leave.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leave.model.service.ClassService;

@SpringJUnitConfig(locations = "/root-config.xml")
@Sql(scripts = "/sql/truncate.sql")
public class ClassServiceTest {
	
	@Autowired
	private ClassService clsService;
	
	@ParameterizedTest
	@CsvSource(value = "Ma Ma,2022-12-02,2022-12-10,3")
	@Sql(scripts = {
			"/sql/truncate.sql",	
			"/sql/teacher.sql"
	})
	void classSearchTest(String teacherName,LocalDate from,LocalDate to,int count) {
		
		var result = clsService.search(Optional.ofNullable(teacherName),Optional.ofNullable(from),Optional.ofNullable(to));
		
		assertEquals(count, result.size());
		
	}

}
