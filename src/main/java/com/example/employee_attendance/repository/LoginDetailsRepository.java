package com.example.employee_attendance.repository;

import com.example.employee_attendance.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
}
