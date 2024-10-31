package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_MASTER")
public class RoleMaster {
 
	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_ROLE_MASTER", allocationSize = 1)
	private int roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	@Column(name = "APP_NAME")
	private String appName ;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "RoleMaster [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", appName="
				+ appName + "]";
	}
}
