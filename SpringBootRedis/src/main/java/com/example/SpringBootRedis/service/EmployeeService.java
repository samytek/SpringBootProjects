package com.example.SpringBootRedis.service;

import com.example.SpringBootRedis.entity.Employee;
import com.example.SpringBootRedis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployeeDetails(Employee employee) throws Exception {
        employeeRepository.save(employee);
    }
}
