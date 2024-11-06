package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.LoginService;

@Controller
@CrossOrigin
public class LoginController {

	@Autowired
	LoginService loginService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	public ResponseEntity<String> validateLogin(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSONObj = loginService.validateLogin(requestJSONObj);
		return new ResponseEntity(responseJSONObj.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSONObj = loginService.logout(requestJSONObj);
		return new ResponseEntity(responseJSONObj.toString(), HttpStatus.OK);
	}
}
