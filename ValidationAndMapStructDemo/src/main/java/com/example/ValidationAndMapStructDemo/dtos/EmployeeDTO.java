package com.example.ValidationAndMapStructDemo.dtos;

import com.example.ValidationAndMapStructDemo.ValidatorGroups.CreateDetailsValidator;
import com.example.ValidationAndMapStructDemo.ValidatorGroups.UniqueEmailValidator;
import com.example.ValidationAndMapStructDemo.ValidatorGroups.UpdateDetailsValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
	
	private int id;
	
	@NotBlank(message="Name cannot be blank for an employee", groups = {UpdateDetailsValidator.class})
	private String name;
	
	@Min(value=20, message="Minimum age should be 20", groups = {CreateDetailsValidator.class})
	@Max(value = 100, message="Max age should be 100", groups = {CreateDetailsValidator.class})
	private int age;
	
	@Email
	@NotBlank(message="Email cant be null", groups = {UpdateDetailsValidator.class})
	@UniqueEmailValidator(message = "Email must be unique", groups = {CreateDetailsValidator.class, UpdateDetailsValidator.class})
	private String emailAddress;
	
	
	private int projectId;
	

}
