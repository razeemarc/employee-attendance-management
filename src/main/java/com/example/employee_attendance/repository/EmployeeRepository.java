package com.example.employee_attendance.repository;

import com.example.employee_attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
    Optional<Employee> findByEmail(String email);
}
