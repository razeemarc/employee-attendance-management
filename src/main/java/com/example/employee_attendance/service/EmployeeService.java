package com.example.employee_attendance.service;

import com.example.employee_attendance.entity.Accounts;
import com.example.employee_attendance.entity.Employee;
import com.example.employee_attendance.repository.AccountsRepository;
import com.example.employee_attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountsRepository accountsRepository;

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
        employee.setLoginDate(null);
        employee.setPresentDays(0);
        employeeRepository.save(employee);
    }

    public void updateLoginTimeAndPresent(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if (employee != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            employee.setLoginDate(currentTime);
            employee.setPresentDays(employee.getPresentDays() + 1);
            employeeRepository.save(employee);

            // Update or create an account record
            updateAccounts(employee);
        }
    }

    // Update or create account records for the employee
    public void updateAccounts(Employee employee) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        double salary = employee.getOnedaySalary() * employee.getPresentDays();

        Accounts account = accountsRepository.findByEmployeeAndYearAndMonth(employee, year, month);

        if (account == null) {
            account = new Accounts();
            account.setEmployee(employee);
            account.setYear(year);
            account.setMonth(month);
        }

        account.setSalary(salary);
        accountsRepository.save(account);
    }

    public List<Accounts> getAllAccounts() {
        return accountsRepository.findAll();
    }
}
