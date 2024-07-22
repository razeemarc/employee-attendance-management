package com.example.employee_attendance.controller;

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

            // Generate a token after successful registration
            final UserDetails userDetails = userDetailsService.loadUserByUsername(employee.getUsername());
            final String token = jwtUtil.generateToken(userDetails.getUsername());

            return new JwtResponse(token);
        } catch (Exception e) {
            return new JwtResponse(e.getMessage());  // Adjust the response as needed
        }
    }

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails.getUsername());
        employeeService.updateLoginTimeAndPresent(jwtRequest.getUsername());

        return new JwtResponse(token);
    }
}
