package com.example.demo.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.RoleMaster;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRoles;
import com.example.demo.repository.RoleMasterDAO;
import com.example.demo.repository.UserDetailsDAO;
import com.example.demo.repository.UserRolesDAO;
import com.example.demo.utils.CommonUtils;

@Service
public class UserRolesServiceImpl implements UserRolesService {

	private static final Logger logger = LoggerFactory.getLogger(UserRolesServiceImpl.class);

	@Autowired
	UserRolesDAO userRolesDAO;

	@Autowired
	RoleMasterDAO roleMasterDAO;

	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Override
	public JSONObject getUserMapping(JSONObject requestJSON) {
		JSONObject responseJSON = new JSONObject();
		String empId = requestJSON.optString("empId");
		String action = requestJSON.optString("action");
		JSONArray jsonRoleIdList = requestJSON.getJSONArray("roleIdList");
		if (action.equalsIgnoreCase("Add")) {
			try {
				List<UserDetails> userDetailsList = userDetailsDAO.findByEmpId(empId);
				if (CommonUtils.isNotNullAndNotEmpty(userDetailsList)) {
					for (Object jsonRoleIdInst : jsonRoleIdList) {
						RoleMaster roleMasterInst = roleMasterDAO
								.findByRoleId(Integer.parseInt(jsonRoleIdInst.toString()));
						if (CommonUtils.isNotNullAndNotEmpty(roleMasterInst)) {
							UserRoles userRoles = new UserRoles();
							userRoles.setRoleId(roleMasterInst.getRoleId());
							userRoles.setRoleName(roleMasterInst.getRoleDesc());
							userRoles.setUserId(Integer.parseInt(empId));
							userRoles.setAppName("DEMO_APP");
							userRolesDAO.save(userRoles);
						}
					}
					responseJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
					responseJSON.put(AppConstants.MESSAGE, "Roles Addedd Successfully");
				} else {
					responseJSON.put(AppConstants.STATUS, AppConstants.FAILED);
					responseJSON.put(AppConstants.MESSAGE, "User Id Not Found");
				}
			} catch (Exception e) {
				responseJSON.put(AppConstants.STATUS, AppConstants.FAILED);
				responseJSON.put(AppConstants.MESSAGE, "Roles Added Failed");
				logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			}
		} else if (action.equalsIgnoreCase("Revoke")) {
			try {
				for (Object jsonRoleIdInst : jsonRoleIdList) {
					UserRoles userRolesInst = userRolesDAO.findByUserIdAndRoleId(Integer.parseInt(empId),Integer.parseInt(jsonRoleIdInst.toString()));
					userRolesDAO.delete(userRolesInst);
				}
				responseJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
				responseJSON.put(AppConstants.MESSAGE, "Roles Revoked Successfully");
			} catch (Exception e) {
				responseJSON.put(AppConstants.STATUS, AppConstants.FAILED);
				responseJSON.put(AppConstants.MESSAGE, "Roles Revoked Failed");
				logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			}
		} else {
			responseJSON.put(AppConstants.STATUS, AppConstants.FAILED);
			responseJSON.put(AppConstants.MESSAGE, "Something Went Wrong");
		}
		return responseJSON;
	}
}
