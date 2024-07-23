package com.example.employee_attendance.controller;

import com.example.employee_attendance.entity.Accounts;
import com.example.employee_attendance.service.AccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accountant")
public class AccountantController {

    @Autowired
    private AccountantService accountantService;

    @GetMapping("/accounts")
    public List<Accounts> getAllAccounts() {
        return accountantService.getAllAccounts();
    }
}
