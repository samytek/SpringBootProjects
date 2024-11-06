package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FILES_DATA_TRACKER")
public class FileDataTracker {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_FILES_DATA_TRACKER", allocationSize = 1)
	private int Id;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_DATA")
	@Lob
	private byte[] fileData;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] bytes) {
		this.fileData = bytes;
	}

	@Override
	public String toString() {
		return "FileDataTracker [Id=" + Id + ", fileName=" + fileName + ", fileData=" + fileData + "]";
	}
}