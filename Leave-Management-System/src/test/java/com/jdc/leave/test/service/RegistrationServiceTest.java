package com.jdc.leave.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leave.model.dto.input.RegistrationForm;
import com.jdc.leave.model.service.RegistrationService;

@SpringJUnitConfig(locations = "/root-config.xml")
public class RegistrationServiceTest {
	
	@Autowired
	private RegistrationService service;
	
	@Deprecated
	@ParameterizedTest
	@CsvSource(value = "4")
	@Sql(scripts = {
			"/sql/truncate.sql","/sql/teacher.sql"
	})
	void findByClassIdTest(int id) {
		var result = service.searchByClassId(id);
		
		assertEquals(0,result.size());
	}
	
	

}
