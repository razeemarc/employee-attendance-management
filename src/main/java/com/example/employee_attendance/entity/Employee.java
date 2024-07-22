package com.example.employee_attendance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private LocalDateTime loginTime;
    private boolean present;

    private Double baseSalary;  // Added baseSalary field

    private String role;  // Added role field

    // Getters and setters
}
