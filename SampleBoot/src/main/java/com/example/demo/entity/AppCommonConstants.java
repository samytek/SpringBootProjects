package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "APP_CONSTANTS")
public class AppCommonConstants {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_APP_CONSTANTS", allocationSize = 1)
	private int Id;

	@Column(name = "CONSTANT_NAME")
	private String constantName;

	@Column(name = "CONSTANT_VALUE")
	private String constantValue;

	public int getTcId() {
		return Id;
	}

	public void setTcId(int tcId) {
		this.Id = tcId;
	}

	public String getConstantName() {
		return constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public String getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}

	@Override
	public String toString() {
		return "AppCommonConstants [Id=" + Id + ", constantName=" + constantName + ", constantValue=" + constantValue
				+ "]";
	}
}