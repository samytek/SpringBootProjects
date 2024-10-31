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
@Table(name = "DEPT_DETAILS")
public class DeptDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "SEQ_DEPT_DETAILS", allocationSize = 1)
	private int id;

	@Column(name = "DEPT_ID")
	private String deptId;

	@Column(name = "DEPT_NAME")
	private String deptName;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "DEPT_DESC")
	private String deptDesc;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "DeptDetails [id=" + id + ", deptId=" + deptId + ", deptName=" + deptName + ", userId=" + userId
				+ ", deptDesc=" + deptDesc + ", createdDate=" + createdDate + "]";
	}
}
