package com.example.ValidationAndMapStructDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ValidationAndMapStructDemo.dtos.EmployeeDTO;
import com.example.ValidationAndMapStructDemo.entities.Employee;
import com.example.ValidationAndMapStructDemo.populator.EmployeePopulator;
import com.example.ValidationAndMapStructDemo.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<Employee> getAllEMployees(){
	
		return employeeRepo.findAll();
	}
	
	public Employee saveEmployee(EmployeeDTO employeeDTO)
	{
		Employee e = EmployeePopulator.INSTANCE.populateEmployee(employeeDTO);
//		e.setCreationDate(new Date());
//		e.s
		return employeeRepo.save(e);
	}
	

}
