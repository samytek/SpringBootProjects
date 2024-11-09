package com.example.MapStructDemoApp.populator;

import com.example.MapStructDemoApp.dto.EmployeeDTO;
import com.example.MapStructDemoApp.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeePopulate {

    Employee populateEmployee(EmployeeDTO employeeDTO);
}
