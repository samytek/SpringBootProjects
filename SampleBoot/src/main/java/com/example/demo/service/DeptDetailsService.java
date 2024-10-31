package com.example.demo.service;

import java.io.FileInputStream;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface DeptDetailsService {

	public JSONObject uploadDeptDeptMaster(MultipartFile file);

	public boolean saveDeptMaster(String fileName, FileInputStream fileInstance);

	public JSONObject getData(JSONObject reqJSON);

	public JSONObject getMonthlyData(JSONObject reqJSON);

	public JSONObject getDetails();
}
