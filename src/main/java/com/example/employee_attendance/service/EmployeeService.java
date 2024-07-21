package com.example.employee_attendance.service;

import com.example.employee_attendance.entity.Employee;
import com.example.employee_attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void registerEmployee(Employee employee) throws Exception {
        if (employeeRepository.findByUsername(employee.getUsername()) != null) {
            throw new Exception("Username is already in use");
        }
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new Exception("Email is already in use");
        }
        employee.setLoginTime(null);
        employee.setPresent(false);
        employeeRepository.save(employee);
    }

    public void updateLoginTimeAndPresent(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null) {
            employee.setLoginTime(LocalDateTime.now());
            employee.setPresent(true);
            employeeRepository.save(employee);
        }
    }
}
