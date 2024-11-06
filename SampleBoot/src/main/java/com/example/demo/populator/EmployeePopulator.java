package com.example.demo.populator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;

@Mapper
public interface EmployeePopulator {

	EmployeePopulator INSTANCE = Mappers.getMapper(EmployeePopulator.class);

	EmployeeDTO userToUserDTO(Employee user);

//	@Mapping(target = "id", ignore = true)
	Employee populateEmployee(EmployeeDTO userDTO);
}
