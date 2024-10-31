package com.example.demo.service;

import org.json.JSONObject;

public interface GenerateDataZipService {

	public JSONObject generatingDataReportFile(JSONObject requestJSON);

	public JSONObject downloadZipFile();

}
