package com.example.employee_attendance.repository;

import com.example.employee_attendance.entity.Accounts;
import com.example.employee_attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByEmployeeAndYearAndMonth(Employee employee, int year, int month);
}
