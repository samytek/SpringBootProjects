package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMAIL_OTP_TRACKER")
public class EmailOtpTracker {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "SEQ_EMAIL_OTP_TRACKER", allocationSize = 1)
	private int id;

	@Column(name = "GENERATED_OTP")
	private int generatedOtp;

	@Column(name = "OTP_SEND_ON_EMAIL")
	private String otpSendOnEmail;

	@Column(name = "IS_AUTHENTICATED")
	private boolean isAuthenticated;

	@Column(name = "IS_EXPIRED")
	private boolean isExpired;

	@Column(name = "OTP_GENERATE_DATE_TIME")
	private Date otpGenerateDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGeneratedOtp() {
		return generatedOtp;
	}

	public void setGeneratedOtp(int generatedOtp) {
		this.generatedOtp = generatedOtp;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public Date getOtpGenerateDateTime() {
		return otpGenerateDateTime;
	}

	public void setOtpGenerateDateTime(Date otpGenerateDateTime) {
		this.otpGenerateDateTime = otpGenerateDateTime;
	}

	public String getOtpSendOnEmail() {
		return otpSendOnEmail;
	}

	public void setOtpSendOnEmail(String otpSendOnEmail) {
		this.otpSendOnEmail = otpSendOnEmail;
	}

	@Override
	public String toString() {
		return "EmailOtpTracker [id=" + id + ", generatedOtp=" + generatedOtp + ", otpSendOnEmail=" + otpSendOnEmail
				+ ", isAuthenticated=" + isAuthenticated + ", isExpired=" + isExpired + ", otpGenerateDateTime="
				+ otpGenerateDateTime + "]";
	}
}
