package com.example.demo.service;

import java.io.FileInputStream;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UserDetailsService {

	public boolean saveUserMaster(String fileName, FileInputStream fileInstance);

	public JSONObject uploadUserMaster(MultipartFile file);

	public JSONObject getDetails();

}
