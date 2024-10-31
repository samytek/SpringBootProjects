package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.utils.CommonUtils;

@Service
public class DataProcessServiceImpl implements DataProcessService {

	private static final Logger logger = LoggerFactory.getLogger(DataProcessServiceImpl.class);
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	DeptDetailsService deptDetailsService;
	
	@SuppressWarnings("unused")
	@Override
	public void uploadMasterFilesToDB(String filePath, String fileName) {
		String fileExtension = FilenameUtils.getExtension(fileName);
		String absoluteFileName = filePath + fileName;
		try {
			boolean isFileExists = new File(absoluteFileName).exists();
			if (!isFileExists) {
				logger.info("Error! File Doesn't Exist on Path :" + absoluteFileName);
			} else {
				String fileNameSplit = fileName.split("_")[0];
				String moduleType = FilenameUtils.getBaseName(fileNameSplit);

				boolean fileProcessed = false;
				FileInputStream fileInstance = new FileInputStream(new File(filePath + fileName));
				switch (moduleType.toUpperCase()) {
						case AppConstants.USER	 : fileProcessed = userDetailsService.saveUserMaster(absoluteFileName,fileInstance); break;
						case AppConstants.DEPT	 : fileProcessed = deptDetailsService.saveDeptMaster(absoluteFileName,fileInstance); break;
				default:
					break;
				}
				fileInstance.close();
				if (fileProcessed == true) {
					logger.info("File Uploaded Successfully :" + absoluteFileName);
					CommonUtils.moveFile(absoluteFileName, filePath + "archive/" + fileName);
				} else {
					logger.info("File Uploaded Successfully :" + absoluteFileName);
					CommonUtils.moveFile(absoluteFileName, filePath + "error/" + "Err_" + fileName);
				}
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {
			}.getClass(), e));
		}
	}
}
