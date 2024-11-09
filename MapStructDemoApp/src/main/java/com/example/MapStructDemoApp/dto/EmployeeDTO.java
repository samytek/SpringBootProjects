package com.example.MapStructDemoApp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {

    private int id;

    @NotBlank(message="Name cannot be blank for an employee")
    private String name;

    @Min(value=20, message="Minimum age should be 20")
    @Max(value = 100, message="Max age should be 100")
    private int age;

    @Email
    @NotBlank(message="Email cant be null")
    private String emailAddress;

    private int projectId;
    
}