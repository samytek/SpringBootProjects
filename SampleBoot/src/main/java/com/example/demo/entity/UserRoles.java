package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles {
	
	@Id
	@Column(name="UR_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_USER_ROLES", allocationSize = 1)
	private int urId;
	
	@Column(name = "IS_DEFAULT") 
	private int isDefault;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "APP_NAME")
	private String appName;

	public int getUrId() {
		return urId;
	}

	public void setUrId(int urId) {
		this.urId = urId;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "UserRoles [urId=" + urId + ", isDefault=" + isDefault + ", userId=" + userId + ", roleId="
				+ roleId + ", roleName=" + roleName + ", appName=" + appName + "]";
	}	
}
