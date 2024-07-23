package com.example.employee_attendance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Employee {

    @Id
    private Long empId; // Manual ID assignment

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private LocalDateTime loginDate;
    private Integer presentDays; // Changed to Integer
    private Double baseSalary;
    private Double onedaySalary;
    private String role;

    // Getters and setters
}
