package com.example.employee_attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.employee_attendance.entity")
@EnableJpaRepositories("com.example.employee_attendance.repository")
public class EmployeeAttendanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeAttendanceApplication.class, args);
	}
}
