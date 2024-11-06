package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DeptDetails;

public interface DeptDetailsDAO extends JpaRepository<DeptDetails, Long> {
	
	List<DeptDetails> findAll();
	List<DeptDetails> findByCreatedDateBetweenOrderByCreatedDateDesc(Date sDate, Date eDate);
}