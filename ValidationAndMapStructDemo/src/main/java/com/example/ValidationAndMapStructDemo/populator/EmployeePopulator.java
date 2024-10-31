package com.example.ValidationAndMapStructDemo.populator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ValidationAndMapStructDemo.dtos.EmployeeDTO;
import com.example.ValidationAndMapStructDemo.entities.Employee;

@Mapper
public interface EmployeePopulator {
	
	EmployeePopulator INSTANCE = Mappers.getMapper(EmployeePopulator.class);
	
	@Mapping(target = "creationDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	Employee populateEmployee(EmployeeDTO employeeDTO);

}
