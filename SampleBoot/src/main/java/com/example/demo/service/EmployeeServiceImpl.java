package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.populator.EmployeePopulator;
import com.example.demo.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getAllEMployees() {
		return employeeRepo.findAll();
	}

	public Employee saveEmployee(EmployeeDTO employeeDTO) {
		Employee e = EmployeePopulator.INSTANCE.populateEmployee(employeeDTO);
		return employeeRepo.save(e);
	}

}
