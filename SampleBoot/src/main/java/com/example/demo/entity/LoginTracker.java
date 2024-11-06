package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOGIN_TRACKER")
public class LoginTracker {

	@Id
	@Column(name = "LT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_LOGIN_TRACKER", allocationSize = 1)
	private int ltId;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "ATTEMPT_DT")
	private Date attemptDt;

	@Column(name = "IS_SUCCESS")
	private int isSuccess;

	public int getLtId() {
		return ltId;
	}

	public void setLtId(int ltId) {
		this.ltId = ltId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUploadedByUserName() {
		return userName;
	}

	public void setUploadedByUserName(String userName) {
		this.userName = userName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getAttemptDt() {
		return attemptDt;
	}

	public void setAttemptDt(Date attemptDt) {
		this.attemptDt = attemptDt;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "LoginTracker [ltId=" + ltId + ", loginId=" + loginId + ", userId=" + userId + ", userName=" + userName
				+ ", ipAddress=" + ipAddress + ", attemptDt=" + attemptDt + ", isSuccess=" + isSuccess + "]";
	}

}
