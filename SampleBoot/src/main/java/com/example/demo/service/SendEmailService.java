package com.example.demo.service;

import org.json.JSONObject;

public interface SendEmailService {

	public JSONObject verifyOTP(JSONObject requestJson);

	public JSONObject sendEmail(JSONObject jsonReq);
}
