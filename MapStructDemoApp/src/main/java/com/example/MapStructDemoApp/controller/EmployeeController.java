package com.example.MapStructDemoApp.controller;

import com.example.MapStructDemoApp.dto.EmployeeDTO;
import com.example.MapStructDemoApp.entity.Employee;
import com.example.MapStructDemoApp.repository.EmployeeRepository;
import com.example.MapStructDemoApp.service.EmployeeService;
import com.example.MapStructDemoApp.validator.ValidateByDeptName;
import com.example.MapStructDemoApp.validator.ValidateByName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/getMessage")
    public String getMessage() {
        return "Success";
    }

    @PostMapping("/addEmployees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDTO empDto) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(empDto), HttpStatus.CREATED);
    }

    @PostMapping("/validateByName")
    public ResponseEntity<Employee> validateByName(@Validated(ValidateByName.class) @RequestBody EmployeeDTO empDto) {
        Employee emp = employeeRepository.findByName(empDto.getName());
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }

    @PostMapping("/validateByDeptName")
    public ResponseEntity<Employee> validateByDeptName(@Validated(ValidateByDeptName.class) @RequestBody EmployeeDTO empDto) {
        Employee emp = employeeRepository.findByDeptName(empDto.getName());
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }

    @PostMapping("/validateByDeptNameAndName")
    public ResponseEntity<Employee> validateByDeptNameAndName(@Valid @RequestBody EmployeeDTO empDto) {
        Employee emp = employeeRepository.findByDeptNameAndName(empDto.getDeptName(), empDto.getName());
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }
}
