package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.model.LoginTracker;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserSession;
import com.example.demo.repository.LoginTrackerDAO;
import com.example.demo.repository.UserDetailsDAO;
import com.example.demo.repository.UserSessionDAO;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.ExceptionUtils;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	MasterService masterService;	
	
	@Autowired
	UserSessionDAO userSessionDAO;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	LoginTrackerDAO loginTrackerDAO;
	
	@Override
	public JSONObject validateLogin(JSONObject dataJSONObj) {
		JSONObject responseDataJSONObj = new JSONObject();
		JSONObject dataJSONObject = dataJSONObj.getJSONObject("data");
		String loginId 						= dataJSONObject.optString("loginId");
		String password 					= dataJSONObject.optString("password");
		UserDetails userDetails 			= new UserDetails();
		List<UserDetails> userDetailsList 	= new ArrayList<UserDetails>();
		
		if(CommonUtils.isNullOrEmpty(loginId) || CommonUtils.isNullOrEmpty(password)) {
			ExceptionUtils.throwDeniedRequestException("deniedAccess", "Access Denied", "Invalid Username or Password");
		}
		String loginCredentials = masterService.getPropertyValue(AppConstants.LOGIN_CREDENTIALS);
		if (CommonUtils.isNotNullAndNotEmpty(loginCredentials) && loginCredentials.equals(AppConstants.ZERO)) {
			userDetailsList = userDetailsDAO.findByEmpId(loginId);
		} else if (CommonUtils.isNotNullAndNotEmpty(loginCredentials) && loginCredentials.equals(AppConstants.ONE)) {
			userDetailsList = userDetailsDAO.findByUserEmail(loginId);
		}
		if(userDetailsList.size() > 0) {
			userDetails = userDetailsList.get(0);
		}
		String errMessage = "";
		String encPassword = CommonUtils.base64Encode(password);
		if (Integer.parseInt(userDetails.getEmpId()) == 0 || !encPassword.equals(userDetails.getLastPswd())) {
			errMessage 		=  "Invalid Username or Password";
			responseDataJSONObj.put(AppConstants.MESSAGE ,"Invalid Username or Password");
			responseDataJSONObj.put(AppConstants.STATUS ,"failed");
			return responseDataJSONObj;
		}
		
		saveLoginTracker(loginId, errMessage, "", userDetails);
		
		int updateLogin = saveSession(dataJSONObj);
		
		if(updateLogin > 0) {
			responseDataJSONObj.put(AppConstants.MESSAGE ,"You have been successfully logged into the application");
			responseDataJSONObj.put(AppConstants.STATUS ,"success");

		}else { //failure
			ExceptionUtils.throwDeniedRequestException("deniedAccess", "Access Denied", "Ahhh!! Unable to create session for you");
		}
		return responseDataJSONObj;
	}

	private void saveLoginTracker(String loginId, String errMessage, String ipAddress, UserDetails userDetails) {
		if (!errMessage.equals("")) {
			LoginTracker loginTracker = new LoginTracker();
			loginTracker.setAttemptDt(new Date());
			loginTracker.setIpAddress(ipAddress);
			loginTracker.setLoginId(loginId);
			loginTracker.setUploadedByUserName(Integer.parseInt(userDetails.getEmpId()) == 0 ? "Unknown" : userDetails.getUserName());
			loginTracker.setIsSuccess(0);
			loginTracker.setUserId(userDetails == null ? -1 : Integer.parseInt(userDetails.getEmpId()) );
			loginTrackerDAO.save(loginTracker);
		} else if (errMessage.equals("")) {
			LoginTracker loginTracker = new LoginTracker();
			loginTracker.setAttemptDt(new Date());
			loginTracker.setIpAddress(ipAddress);
			loginTracker.setLoginId(loginId);
			loginTracker.setUploadedByUserName(userDetails.getUserName());
			loginTracker.setIsSuccess(1);
			loginTracker.setUserId(Integer.parseInt(userDetails.getEmpId()));
			loginTrackerDAO.save(loginTracker);
		}
	}
	
	private int saveSession(JSONObject dataJSONObj) {
		UserSession userSession = new UserSession();
		JSONObject dataJSONObject = dataJSONObj.getJSONObject("data");
		userSession.setUserId(dataJSONObject.optInt("loginId"));
		userSession.setCurrentRole("MAKER");
		userSession.setAccessToken(CommonUtils.generateRandomString());
		Date timeRightNow = new Date();
		userSession.setLoginTime(timeRightNow);
		userSession.setLastAccessTime(timeRightNow);
		userSession.setIsSessionLive(1);
		UserSession userSessionInst = userSessionDAO.save(userSession);
		int updateLogin = userSessionInst.getUsId();
		return updateLogin;
	}
	
	@Override
	public JSONObject logout(JSONObject requestJSONObj) {
		JSONObject statusJSONObj 	= new JSONObject();
		JSONObject userInfoJSONObj 	= requestJSONObj.getJSONObject("userInfo");		
		int userId 					= userInfoJSONObj.optInt("userId");
		String accessToken 			= userInfoJSONObj.optString("accessToken");
		String currentRole 			= userInfoJSONObj.optString("userRole");
		int update 					= 0;
		UserSession userSession 	= userSessionDAO.findByUserIdAndCurrentRoleAndAccessToken(userId, currentRole, accessToken);
		if(userSession != null) {
			userSession.setAccessToken("");
			userSession.setIsSessionLive(0);
			userSession.setLastAccessTime(new Date());
			UserSession userSessionInst = userSessionDAO.save(userSession);
			update = userSessionInst.getUsId();
		}
		if(update > 0) {
			statusJSONObj.put(AppConstants.STATUS, AppConstants.SUCCESS);
			statusJSONObj.put(AppConstants.MESSAGE,"You have been successfully logged out of the application");
		}else { //failure
			statusJSONObj.put(AppConstants.STATUS,AppConstants.FAILED);
			statusJSONObj.put(AppConstants.MESSAGE,"Somethig went wrong");
		}
		return statusJSONObj;
	}
}
