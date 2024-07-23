package com.example.employee_attendance.service;

import com.example.employee_attendance.entity.Employee;
import com.example.employee_attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AccountScheduler {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Schedule this method to run periodically (e.g., daily, weekly)
    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void updateAllAccounts() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            employeeService.updateAccounts(employee);
        }
    }
}
