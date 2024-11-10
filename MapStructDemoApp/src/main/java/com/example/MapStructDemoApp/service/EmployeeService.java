package com.example.MapStructDemoApp.service;

import com.example.MapStructDemoApp.dto.EmployeeDTO;
import com.example.MapStructDemoApp.entity.Employee;
import com.example.MapStructDemoApp.populator.EmployeePopulate;
import com.example.MapStructDemoApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeePopulate employeePopulate;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee e = employeePopulate.populateEmployee(employeeDTO);
        return employeeRepository.save(e);
    }
}
