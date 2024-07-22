package com.example.employee_attendance.service;

import com.example.employee_attendance.entity.Employee;
import com.example.employee_attendance.entity.LoginDetails;
import com.example.employee_attendance.repository.EmployeeRepository;
import com.example.employee_attendance.repository.LoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LoginDetailsRepository loginDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerEmployee(Employee employee) throws Exception {
        if (employeeRepository.findByUsername(employee.getUsername()) != null) {
            throw new Exception("Username is already in use");
        }
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new Exception("Email is already in use");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setLoginTime(null);
        employee.setPresent(false);
        employeeRepository.save(employee);
    }

    public void updateLoginTimeAndPresent(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            employee.setLoginTime(currentTime);
            employee.setPresent(true);
            employeeRepository.save(employee);

            // Save login details
            LoginDetails loginDetails = new LoginDetails();
            loginDetails.setName(employee.getUsername());
            loginDetails.setEmail(employee.getEmail());
            loginDetails.setLoginTime(currentTime);
            loginDetails.setPresent(true);
            loginDetails.setEmployee(employee);
            loginDetailsRepository.save(loginDetails);
        }
    }
}
