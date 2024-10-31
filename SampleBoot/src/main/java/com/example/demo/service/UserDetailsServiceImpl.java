package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.UserDetailsDAO;
import com.example.demo.utils.CommonUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Override
	public JSONObject uploadUserMaster(MultipartFile file) {
		JSONObject responseJson = new JSONObject();
		try {
			String fileName = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(fileName);
			String rootPath = AppConstants.ROOT_PATH;

			boolean isValidFileExtension = getFileDetailsByModuleName(fileExtension);
			if (!isValidFileExtension) {
				responseJson.put(AppConstants.RESPONSE_MESSAGE, "Invalid file format");
				responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
				return responseJson;
			}
			String fileNameSplit = fileName.split("_")[0];
			String moduleType = FilenameUtils.getBaseName(fileNameSplit);
			if (!AppConstants.USER.equalsIgnoreCase(moduleType)) {
				responseJson.put(AppConstants.RESPONSE_MESSAGE, "Invalid file Type");
				responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
				return responseJson;
			}

			boolean isDuplicateFile = true;
			if (isDuplicateFile) {
				responseJson.put(AppConstants.RESPONSE_MESSAGE, "File Already Uploaded.");
				responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
				return responseJson;
			}
			String filePath = rootPath + "/";
			logger.info("::::: File Path ::::: " + filePath);
			File fileDir = new File(filePath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			String absoluteFilePath = filePath + File.separator + fileName;
			File destinationFile = new File(absoluteFilePath);
			file.transferTo(destinationFile);

			responseJson.put(AppConstants.RESPONSE_MESSAGE, "File Uploaded Successfully.");
			responseJson.put(AppConstants.STATUS, AppConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		return responseJson;
	}

	public boolean getFileDetailsByModuleName(String moduleName) {
		boolean isValidFileExtension = Boolean.FALSE;
		if (CommonUtils.isNotNullAndNotEmpty(moduleName) && (moduleName.equalsIgnoreCase(AppConstants.CSV_EXTENSION))) {
			isValidFileExtension = Boolean.TRUE;
		}
		return isValidFileExtension;
	}

	@SuppressWarnings({ "finally", "unused" })
	@Override
	public boolean saveUserMaster(String fileName, FileInputStream fileInstance) {
		boolean fileProcessed = false;
		int processed = 0;
		try {
			JSONArray userDataList = CommonUtils.convertCSVToJson(fileName, AppConstants.COMMA_SEPARATOR, true);
			int tableLength = userDataList.length();
			for (int i = 0; i < tableLength; i++) {
				JSONObject userDataInst = userDataList.getJSONObject(i);
				UserDetails userDetails = new UserDetails();
				userDetails.setEmpId(userDataInst.optString("EMP ID"));
				userDetails.setUserName(userDataInst.optString("USER NAME"));
				userDetails.setUserRole(userDataInst.optString("USER ROLE"));
				userDetails.setUserEmail(userDataInst.optString("USER EMAIL"));
				userDetails.setMobNo(userDataInst.optString("MOBILE NO"));
				userDetails.setDeptName(userDataInst.optString("DEPT"));
//				String encodedPswdString = Base64.getEncoder().encodeToString(userDataInst.optString("LAST PSWD").getBytes());
//				userDetails.setLastPswd(encodedPswdString);
				userDetailsDAO.save(userDetails);
				processed++;
			}
			fileProcessed = true;
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		} finally {
			return fileProcessed;
		}
	}
	
	@Override
	public JSONObject getDetails() {
		JSONObject responseJSON = new JSONObject();
		List<UserDetails> userDetailsList = null;
		try {
			userDetailsList = userDetailsDAO.findAll();
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
		responseJSON.put("userDetailsList", userDetailsList);
		return responseJSON;
	}
}
