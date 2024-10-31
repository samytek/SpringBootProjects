package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EmailOtpTracker;

public interface EmailOtpTrackerDAO extends JpaRepository<EmailOtpTracker, Long> {

	EmailOtpTracker findByGeneratedOtpAndOtpSendOnEmail(int otp, String emailId);
}