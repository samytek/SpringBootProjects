package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FileDataTracker;

public interface FileDataTrackerDAO extends JpaRepository<FileDataTracker, Long> {

	FileDataTracker findByFileName(String fileName);
}