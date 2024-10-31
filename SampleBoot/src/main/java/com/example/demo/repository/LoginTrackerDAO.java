package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LoginTracker;

public interface LoginTrackerDAO extends JpaRepository<LoginTracker, Long> {
	
}