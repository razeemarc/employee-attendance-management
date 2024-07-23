package com.example.employee_attendance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_Id")
    private Employee employee;
    private int year;
    private int month;
    private Double salary;

    // Getters and setters
}
