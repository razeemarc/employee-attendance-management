package com.example.employee_attendance;

import org.springframework.boot.SpringApplication;

public class TestEmployeeAttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeeAttendanceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
