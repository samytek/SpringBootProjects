package com.example.demo.service;

import org.json.JSONObject;

public interface LoginService {

	public JSONObject validateLogin(JSONObject dataJSONObj);

	public JSONObject logout(JSONObject requestJSONObj);
}
