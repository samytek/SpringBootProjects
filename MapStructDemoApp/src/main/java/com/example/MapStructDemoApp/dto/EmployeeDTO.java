package com.example.MapStructDemoApp.dto;

import com.example.MapStructDemoApp.validator.ValidateByDeptName;
import com.example.MapStructDemoApp.validator.ValidateByName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class EmployeeDTO {

    private int id;

    @NotBlank(message="Name cannot be blank for an employee", groups = {Default.class, ValidateByName.class})
    private String name;

    @NotBlank(message="Department cannot be blank for an employee", groups = {Default.class, ValidateByDeptName.class})
    private String deptName;

    @Min(value=20, message="Minimum age should be 20")
    @Max(value = 100, message="Max age should be 100")
    private int age;

    @Email
    @NotBlank(message="Name cannot be blank")
    private String emailAddress;

    private int projectId;
    
}