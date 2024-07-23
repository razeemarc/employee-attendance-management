package com.example.employee_attendance.controller;

import com.example.employee_attendance.entity.Accounts;
import com.example.employee_attendance.entity.Employee;
import com.example.employee_attendance.models.JwtRequest;
import com.example.employee_attendance.models.JwtResponse;
import com.example.employee_attendance.security.JwtUtil;
import com.example.employee_attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public JwtResponse registerEmployee(@RequestBody Employee employee) {
        try {
            employeeService.registerEmployee(employee);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(employee.getEmail());
            final String token = jwtUtil.generateToken(userDetails.getUsername());

            return new JwtResponse(token);
        } catch (Exception e) {
            return new JwtResponse(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails.getUsername());
        employeeService.updateLoginTimeAndPresent(jwtRequest.getEmail());

        return new JwtResponse(token);
    }

    @GetMapping("/accounts")
    public List<Accounts> getAllAccounts() {
        return employeeService.getAllAccounts();
    }
}
