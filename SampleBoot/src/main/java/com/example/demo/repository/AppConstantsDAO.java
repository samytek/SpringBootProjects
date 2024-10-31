package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AppCommonConstants;

public interface AppConstantsDAO extends JpaRepository<AppCommonConstants, Long> {
	
	public AppCommonConstants findConstantByConstantName(String constantName);
}