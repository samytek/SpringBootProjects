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
@Table(name = "USER_SESSION")
public class UserSession {
	@Id
	@Column(name = "US_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_USER_SESSION", allocationSize = 1)
	private int usId;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "CURR_ROLE")
	private String currentRole;

	@Column(name = "ACCESS_TOKEN")
	private String accessToken;

	@Column(name = "LOGIN_TIME")
	private Date loginTime;

	@Column(name = "LAST_ACCESS_TIME")
	private Date lastAccessTime;

	@Column(name = "IS_SESSION_LIVE")
	private int isSessionLive;

	public int getIsSessionLive() {
		return isSessionLive;
	}

	public void setIsSessionLive(int isSessionLive) {
		this.isSessionLive = isSessionLive;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	@Override
	public String toString() {
		return "UserSession [usId=" + usId + ", userId=" + userId + ", currentRole=" + currentRole + ", accessToken="
				+ accessToken + ", loginTime=" + loginTime + ", lastAccessTime=" + lastAccessTime + ", isSessionLive="
				+ isSessionLive + "]";
	}
}