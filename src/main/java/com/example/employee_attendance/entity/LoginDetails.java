package com.example.employee_attendance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class LoginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private LocalDateTime loginTime;
    private boolean present;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
