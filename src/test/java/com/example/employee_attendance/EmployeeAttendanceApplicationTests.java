package com.example.employee_attendance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class EmployeeAttendanceApplicationTests {

	@Test
	void contextLoads() {
	}

}
