package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.model.AppCommonConstants;
import com.example.demo.repository.AppConstantsDAO;
import com.example.demo.utils.CommonUtils;

@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	AppConstantsDAO appConstantsDAO;
	
	@Override
	public String getPropertyValue(String propertyKey) {
		
		switch(propertyKey) {
		case AppConstants.ROOT_PATH 			: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.rootPath)) 			return AppConstants.rootPath;  				break ;
		case AppConstants.SENDER_MAIL_ID 		: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.senderMailId)) 		return AppConstants.senderMailId;    		break ;
		case AppConstants.SENDER_PWD 			: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.senderPwd)) 		return AppConstants.senderPwd; 				break ;
		case AppConstants.SMTP_HOST 			: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.smtpHost)) 			return AppConstants.smtpHost;				break ;
		case AppConstants.SMTP_PORT 			: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.smtpPort)) 			return AppConstants.smtpPort;				break ;
		case AppConstants.FILE_GENERATOR_FLAG 	: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.fileGeneratorFlag)) return AppConstants.fileGeneratorFlag;  	break ;
		case AppConstants.LOGIN_CREDENTIALS 	: if (CommonUtils.isNotNullAndNotEmpty(AppConstants.loginCredentials)) 	return AppConstants.loginCredentials;  		break ;
		default : break;
	}
		
		String propertyValue = AppConstants.BLANK;
		AppCommonConstants appCommonConstants = null;
		appCommonConstants = appConstantsDAO.findConstantByConstantName(propertyKey);
		if(CommonUtils.isNotNullAndNotEmpty(appCommonConstants)) {
			propertyValue = appCommonConstants.getConstantValue();
		}
		
		switch(propertyKey) {
		case AppConstants.ROOT_PATH 				: AppConstants.rootPath = propertyValue; 		  	break ;
		case AppConstants.SENDER_MAIL_ID 			: AppConstants.senderMailId = propertyValue; 	  	break ;
		case AppConstants.SENDER_PWD 				: AppConstants.senderPwd = propertyValue; 		  	break ;
		case AppConstants.SMTP_HOST 				: AppConstants.smtpHost = propertyValue; 		  	break ;
		case AppConstants.SMTP_PORT 				: AppConstants.smtpPort = propertyValue; 		  	break ;
		case AppConstants.FILE_GENERATOR_FLAG 		: AppConstants.fileGeneratorFlag = propertyValue; 	break ;
		case AppConstants.LOGIN_CREDENTIALS 		: AppConstants.loginCredentials = propertyValue; 	break ;
		default : break;
	}
		return propertyValue;
	}
}
