package com.example.SpringBootRedis.controller;

import com.example.SpringBootRedis.entity.Employee;
import com.example.SpringBootRedis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) throws Exception {
        employeeService.saveEmployeeDetails(employee);
        return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
    }
}
