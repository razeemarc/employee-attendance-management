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

    private String username;
    private String password;
    private String email;
    private LocalDateTime loginTime;
    private boolean present;

    // Getters and setters
}
