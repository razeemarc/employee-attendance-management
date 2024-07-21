package com.example.employee_attendance.models;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

    // Getters and setters
}
