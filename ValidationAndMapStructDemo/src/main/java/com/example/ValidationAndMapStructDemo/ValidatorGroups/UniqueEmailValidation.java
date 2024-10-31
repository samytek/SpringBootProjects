package com.example.ValidationAndMapStructDemo.ValidatorGroups;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ValidationAndMapStructDemo.repo.EmployeeRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmailValidator, String> {
	
	@Autowired
	EmployeeRepo employeeRepo ;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(employeeRepo.findByEmailAddress(value).size() == 0)
			return true;
		return false;
	}

}