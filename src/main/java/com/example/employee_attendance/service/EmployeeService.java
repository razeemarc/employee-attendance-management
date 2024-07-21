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

    public void registerEmployee(Employee employee) {
        // Save employee with initial loginTime as null and present as false
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
