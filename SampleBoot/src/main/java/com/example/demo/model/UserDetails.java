package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "SEQ_USER_DETAILS", allocationSize = 1)
	private int id;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "DEPT_NAME")
	private String deptName;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "MOBILE_NO")
	private String mobNo;

	@Column(name = "LAST_PSWD")
	private String lastPswd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return roleId;
	}

	public void setUserRole(String roleId) {
		this.roleId = roleId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getLastPswd() {
		return lastPswd;
	}

	public void setLastPswd(String lastPswd) {
		this.lastPswd = lastPswd;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", empId=" + empId + ", userName=" + userName
				+ ", roleId=" + roleId + ", deptName=" + deptName + ", userEmail=" + userEmail + ", mobNo=" + mobNo
				+ ", lastPswd=" + lastPswd + "]";
	}
}
