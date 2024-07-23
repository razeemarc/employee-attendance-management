package com.example.employee_attendance.service;

import com.example.employee_attendance.entity.Accounts;
import com.example.employee_attendance.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountantService {

    @Autowired
    private AccountsRepository accountsRepository;

    public List<Accounts> getAllAccounts() {
        return accountsRepository.findAll();
    }
}
