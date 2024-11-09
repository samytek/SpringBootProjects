package com.example.MapStructDemoApp.controller;

import com.example.MapStructDemoApp.dto.EmployeeDTO;
import com.example.MapStructDemoApp.entity.Employee;
import com.example.MapStructDemoApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployees")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO empDto) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(empDto), HttpStatus.CREATED);
    }
}
