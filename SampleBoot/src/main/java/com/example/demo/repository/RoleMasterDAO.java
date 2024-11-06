package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RoleMaster;

public interface RoleMasterDAO extends JpaRepository<RoleMaster, Long> {
	
	RoleMaster findByRoleId(int roleId);
}