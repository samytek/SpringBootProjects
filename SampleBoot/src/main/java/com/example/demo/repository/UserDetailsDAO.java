package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDetails;

public interface UserDetailsDAO extends JpaRepository<UserDetails, Long> {
	
	List<UserDetails> findAll();
	List<UserDetails> findByEmpId(String loginId);
//	List<UserDetails findByEmpId(String loginId);
	List<UserDetails> findByUserEmail(String emailId);
}